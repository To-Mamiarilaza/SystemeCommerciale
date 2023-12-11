/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package service.stock;

import connection.DBConnection;
import generalisation.GenericDAO.GenericDAO;
import generalisation.utils.GenericUtil;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.List;
import model.article.Article;
import model.stock.ArticleMethodMapping;
import model.stock.Incoming;
import model.stock.Outgoing;

/**
 *
 * @author to
 */
public class MovementService {
    // get the last enter with the sum of quantity of all enter
    public static Incoming getLastEnter(Article article, Connection connection) throws Exception {
        String subQuery = "SELECT SUM(quantity) FROM v_incoming_stock WHERE id_article = %d";
        String query = "SELECT id_incoming, date, id_bde, id_article, (" + subQuery + ") as quantity, unit_price, current_unit_price, etat FROM incoming WHERE id_incoming = (SELECT MAX(id_incoming) FROM incoming WHERE id_article = %d)";
        query = String.format(query, article.getIdArticle(), article.getIdArticle());
        
        List<Incoming> incomings = (List<Incoming>) GenericDAO.directQuery(Incoming.class, query, connection);
        if (incomings.size() == 0) {
            return null;
        }
        return incomings.get(0);
    }
    
    // calcul the current unit price of article by CUMP
    public static void setCurrentUnitPrice(Incoming newInc, Connection connection) throws Exception {
        Incoming lastInc = getLastEnter(newInc.getArticle(), connection);
        if (lastInc == null) {
            newInc.setCurrentUnitPrice(newInc.getUnitPrice());
            return;
        }
        double currentPrice = ((newInc.getQuantity() * newInc.getUnitPrice()) + (lastInc.getQuantity() * lastInc.getCurrentUnitPrice())) / (newInc.getQuantity() + lastInc.getQuantity());
        newInc.setCurrentUnitPrice(currentPrice);
    }
    
    // for adding new stock
    public static void entrer(String date, String idBDE, String idArticle, String quantity, String unitPrice, Connection connection) throws Exception {
        Incoming incoming = new Incoming(date, idBDE, idArticle, quantity, unitPrice);
        setCurrentUnitPrice(incoming, connection);
        GenericDAO.save(incoming, connection);
    }
    
    // outgoing stock
    // Verify the input data validity
    public static void checkAllParameter(String date, String idArticle, String idBDS, String quantity) throws Exception {
        if (date == null || date.trim().equals("")) throw new Exception("La date du sortie de stock ne doit pas être null !");
        if (idArticle == null || idArticle.trim().equals("")) throw new Exception("L' article ne doit pas être null !");
        if (quantity == null || quantity.trim().equals("")) throw new Exception("La quantité ne doit pas être null !");
        if (idBDS == null || idBDS.trim().equals("")) throw new Exception("L' ID du bon de sortie ne doit pas être null !");
    }
    
    // The minimum possible unit price will be given by ajax ( Think about OutRequest Object )
    public static void sortir(String date, String idBDS, String idArticle, String quantity, Connection connection) throws Exception {
        checkAllParameter(date, idArticle, idBDS, quantity);

        // Get all needed value
        LocalDate dateValue = LocalDate.parse(date);
        double quantityValue = Double.valueOf(quantity);
        double unitPriceValue = 0;    // We will think about how to get unit price after
        int idBDSValue = Integer.valueOf(idBDS);

        Article article = GenericDAO.findById(Article.class, idArticle, connection);
        if (article == null) {
            throw new Exception("Il n'y aucun article portant cette id !");
        }
        ArticleMethodMapping mapping = MappingService.getArticleMethod(article, connection);
        String methodName = mapping.getGestionMethod().getGestionMethodName();
        
        // Remaining article stock
        String order = methodName.equals("FIFO") || methodName.equals("CUMP") ? "ASC" : "DESC";
        String query = "SELECT * FROM v_incoming_stock WHERE id_article = '" + article.getIdArticle() + "' AND date <= '" + date + "' ORDER BY date " + order;
        List<Incoming> incomingList = (List<Incoming>) GenericDAO.directQuery(Incoming.class, query, connection);

        for (Incoming incoming : incomingList) {
            if (incoming.getQuantity() < quantityValue) {
                quantityValue -= incoming.getQuantity();
                Outgoing outgoing = new Outgoing(dateValue, idBDSValue, incoming, incoming.getQuantity(), unitPriceValue, 1);
                GenericDAO.save(outgoing, connection);
            } else {
                Outgoing outgoing = new Outgoing(dateValue, idBDSValue, incoming, quantityValue, unitPriceValue, 1);
                GenericDAO.save(outgoing, connection);
                quantityValue = 0;
                break;
            }
        }

        if (quantityValue != 0) {
            connection.rollback();
            throw new Exception("La quantité dans le stock est insuffisant !");
        }
    }
    
    // get all incoming histories
    public static List<Incoming> getAllIncoming(Connection connection) throws Exception {
        return (List<Incoming>) GenericDAO.getAll(Incoming.class, null, connection);
    }
    
    // get all outgoing histories
    public static List<Outgoing> getAllOutgoing(Connection connection) throws Exception {
        return (List<Outgoing>) GenericDAO.getAll(Outgoing.class, null, connection);
    }
    
    public static void insertTestIncoming() throws Exception {
        Connection connection = DBConnection.getConnection();
        entrer("2023-12-02", "0", "3", "5", "2000", connection);
        entrer("2023-12-06", "0", "3", "10", "2500", connection);
        connection.commit();
        connection.close();
    }
    
    public static void insertTestOutgoing() throws Exception {
        Connection connection = DBConnection.getConnection();
        
        connection.commit();
        connection.close();
    }
    
    public static void main(String[] args) throws Exception {
    }
    
}
