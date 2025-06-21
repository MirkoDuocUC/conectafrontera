package cl.duoc.conectafrontera.dto;

public class ErrorResponseDTO {

    // Atributo para almacenar el mensaje de error
    private String mensajeError;

    // Constructor para inicializar el mensaje de error
    public ErrorResponseDTO(String mensajeError) {
        this.mensajeError = mensajeError;
    }

    // Getter para el mensaje de error
    public String getMensajeError() {
        return mensajeError;
    }

    // Setter para el mensaje de error
    public void setMensajeError(String mensajeError) {
        this.mensajeError = mensajeError;
    }
}
