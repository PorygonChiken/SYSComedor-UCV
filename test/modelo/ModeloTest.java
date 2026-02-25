package modelo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ModeloTest {
    @Test
    public void testRegistrarUsuario() {
        modelo miModelo = new modelo();
        String usuario = "AAA_TestUser";
        String cedula = "88888888";
        String contra = "clave123";

        boolean resultadoReal = miModelo.registrarUsuario(usuario, cedula, contra);

        assertEquals(true, resultadoReal, "El sistema debería retornar true al registrar un usuario válido");
    }

    @Test
    public void testVerificarLoginFallido() {
        modelo miModelo = new modelo();
        String usuarioExistente = "AAA_TestUser"; 
        String claveIncorrecta = "hola";

        boolean loginReal = miModelo.verificar(usuarioExistente, claveIncorrecta);

        assertEquals(false, loginReal, "El login debe fallar si la contraseña es incorrecta");
    }

    @Test
    public void testRecargarSaldo() {
        modelo miModelo = new modelo();
        String usuario = "AAA_TestUser";
        double montoRecarga = 500.0;

        boolean recargaExitosa = miModelo.recargarSaldo(usuario, montoRecarga);

        assertEquals(true, recargaExitosa, "La recarga de saldo debería retornar true al completarse");
    }

    @Test
    public void testConsultarSaldoUsuarioInexistente() {
        modelo miModelo = new modelo();
        String fakeUser = "JohnPork";
        String saldoEsperado = "0";

        String saldoReal = miModelo.Saldo(fakeUser);

        assertEquals(saldoEsperado, saldoReal, "Un usuario que no existe debe tener saldo 0");
    }
}