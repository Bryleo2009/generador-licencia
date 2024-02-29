package of.system;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Por favor, introduce la versión del producto:");
        String version = "v" +scanner.nextLine();
        // La fecha de compra es la fecha actual
        Date purchaseDate = new Date();
        System.out.println("Por favor, introduce la fecha y hora de expiración (formato dd/MM/yyyy-HH:mm):");
        String expiryDateTimeString = scanner.nextLine();
        Date expiryDate = null;
        try {
            expiryDate = new SimpleDateFormat("dd/MM/yy-HH:mm").parse(expiryDateTimeString);
        } catch (ParseException e) {
            System.out.println("Fecha y hora de expiración no válidas");
            System.exit(1);
        }

        String token = generateToken(version, purchaseDate, expiryDate);
        System.out.println("Token generado: " + token);
    }
    public static String generateToken(String clientId, Date purchaseDate, Date expiryDate) {
        //Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
        String key = "0fSyst3mD3saRr0ll0D3Pr0gr4m4c10nV3rs10n20240fSyst3mD3saRr0ll0D3Pr0gr4m4c10nV3rs10n2024";
        String jws = Jwts.builder()
                .setSubject(clientId)
                .setIssuedAt(purchaseDate)
                .setExpiration(expiryDate)
                .signWith(SignatureAlgorithm.HS256,key)
                .compact();

        return jws;
    }
}