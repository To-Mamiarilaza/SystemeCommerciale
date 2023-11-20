package service.util;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DisplayUtil {
    // A class to format data for displaying
    private static final String[] unite = {"", "un", "deux", "trois", "quatre", "cinq", "six", "sept", "huit", "neuf", "dix",
        "onze", "douze", "treize", "quatorze", "quinze", "seize", "dix-sept", "dix-huit", "dix-neuf"};
    private static final String[] dizaine = {"", "", "vingt", "trente", "quarante", "cinquante", "soixante", "soixante", "quatre-vingt", "quatre-vingt"};

    public static String toLetter(int nombre) {
        if (nombre == 0) {
            return "zéro";
        }
        if (nombre < 0) {
            return "moins " + toLetter(-nombre);
        }

        String resultat = "";
        if (nombre >= 1000000) {
            resultat += toLetter(nombre / 1000000) + " million";
            if (nombre % 1000000 != 0) {
                resultat += " ";
            } else {
                return resultat;
            }
            nombre %= 1000000;
        }

        if (nombre >= 1000) {
            resultat += toLetter(nombre / 1000) + " mille";
            if (nombre % 1000 != 0) {
                resultat += " ";
            } else {
                return resultat;
            }
            nombre %= 1000;
        }

        if (nombre >= 100) {
            resultat += unite[nombre / 100] + " cent";
            if (nombre % 100 != 0) {
                resultat += " ";
            } else {
                return resultat;
            }
            nombre %= 100;
        }

        if (nombre >= 20) {
            resultat += dizaine[nombre / 10];
            if (nombre % 10 != 0) {
                resultat += "-" + unite[nombre % 10];
            }
        } else {
            resultat += unite[nombre];
        }

        return resultat;
    }

    public static String formatMoney(double amount, String currencyUnit) {
        // Utilise DecimalFormat pour ajouter l'espace entre chaque millier
        DecimalFormatSymbols symbols = new DecimalFormatSymbols();
        symbols.setGroupingSeparator(' '); // Définir l'espace comme séparateur de milliers
        DecimalFormat format = new DecimalFormat("#,###.00", symbols);

        // Formate la quantité d'argent
        String formattedAmount = format.format(amount);

        // Ajoute le symbole de devise
        formattedAmount = formattedAmount + " " + currencyUnit;

        return formattedAmount;
    }

    public static String formatLocalDate(LocalDate localDate) {
        // Spécifie le format "dd-MM-yyyy"
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // Formate le LocalDate en tant que chaîne de caractères
        return localDate.format(formatter);
    }

    public static void main(String[] args) {
        System.out.println(toLetter(252325));
    }
}
