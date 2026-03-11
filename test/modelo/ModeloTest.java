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

    @Test
    public void testTransferirSaldoPanaExitoso() {
       
        modelo miModelo = new modelo();
        
        String origen = "Pana1";
        String cedulaOrigen = "11111111";
        miModelo.registrarUsuario(origen, cedulaOrigen, "clave123");
        miModelo.recargarSaldo(origen, 500.0); 

        String destino = "PanaRecibe";
        String cedulaDestino = "22222222";
        miModelo.registrarUsuario(destino, cedulaDestino, "clave123"); 

        double montoATransferir = 150.0;

        boolean transferenciaExitosa = miModelo.transferirSaldoPana(origen, cedulaDestino, montoATransferir);

        assertEquals(true, transferenciaExitosa, "La transferencia debería retornar true al completarse con saldo suficiente");
    }

    @Test
    public void testTransferirSaldoPanaSinDinero() {
       
        modelo miModelo = new modelo();
        
        String origen = "Pana2";
        String cedulaOrigen = "33333333";
        miModelo.registrarUsuario(origen, cedulaOrigen, "clave123");
       
        String cedulaDestino = "22222222"; 
        double montoATransferir = 1000.0; 

        boolean transferenciaExitosa = miModelo.transferirSaldoPana(origen, cedulaDestino, montoATransferir);

        assertEquals(false, transferenciaExitosa, "La transferencia debe fallar si el origen no tiene saldo suficiente");
    }
}