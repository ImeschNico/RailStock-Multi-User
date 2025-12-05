package com.railStock.rail_stock.ErrorHandling;

/**
 * Exception, die ausgelöst wird, wenn eine angeforderte Ressource nicht gefunden wurde.
 * <p>
 * Diese benutzerdefinierte Laufzeit-Exception wird typischerweise verwendet,
 * um 404-ähnliche Fehlerfälle innerhalb der Anwendung zu signalisieren,
 * beispielsweise wenn ein {@code Bestand}, eine {@code Lok} oder ein
 * {@code Hersteller} mit einer bestimmten ID oder Artikelnummer nicht existiert.
 * </p>
 *
 * <p>
 * Sie kann global über einen {@code @ControllerAdvice}-Handler abgefangen werden,
 * um eine entsprechende HTTP-Antwort (z.&nbsp;B. 404 Not Found) zurückzugeben.
 * </p>
 *
 * @author
 *     Nico Imesch
 * @version
 *     1.0
 */
public class NotFoundException extends RuntimeException {
    /**
     * Erstellt eine neue {@link NotFoundException} mit der angegebenen Fehlermeldung.
     *
     * @param message eine aussagekräftige Beschreibung des Fehlers,
     *                z.&nbsp;B. „Lok mit Artikelnummer 1234 nicht gefunden“
     */
    public NotFoundException(String message) {
        super(message);
    }
}
