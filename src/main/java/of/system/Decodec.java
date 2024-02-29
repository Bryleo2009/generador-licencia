package of.system;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Base64;
import java.util.Date;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // La clave que usaste para codificar el token
        String key = "0fSyst3mD3saRr0ll0D3Pr0gr4m4c10nV3rs10n20240fSyst3mD3saRr0ll0D3Pr0gr4m4c10nV3rs10n2024";

        // Solicita el token que quieres verificar
        System.out.println("Por favor, introduce el token que quieres verificar:");
        String token = scanner.nextLine();

        Claims claims = verifyToken(token, key);
        if (claims != null) {
            System.out.println("El token es válido. Las reclamaciones son:");

            String passKey = claims.getSubject();
            Date fechaCompra = new Date(((Integer)claims.get("iat")).longValue() * 1000);
            Date fechaExpiracion = new Date(((Integer)claims.get("exp")).longValue() * 1000);

            System.out.println("passKey: " + passKey);
            System.out.println("fechaCompra: " + fechaCompra);
            System.out.println("fechaExpiracion: " + fechaExpiracion);
        } else {
            System.out.println("El token no es válido.");
        }
    }

    public static Claims verifyToken(String token, String key) {
        try {
            Jws<Claims> jws = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);

            // Si llegamos hasta aquí, el token es válido
            // Extraemos las reclamaciones del token
            Claims claims = jws.getBody();

            return claims;
        } catch (Exception e) {
            // Si hay una excepción, el token no es válido
            return null;
        }
    }
}