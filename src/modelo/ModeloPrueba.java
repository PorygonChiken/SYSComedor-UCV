package modelo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ModeloPrueba {

    // ==========================================
    // 1. PRUEBAS DE GESTIÓN DE USUARIOS
    // ==========================================

    @Test
    public void testRegistrarUsuario() {
        // 1. ARRANGE (Preparar los datos de prueba)
        modelo miModelo = new modelo();
        String usuario = "AAA_TestUser";
        String cedula = "88888888";
        String contra = "clave123";

        // 2. ACT (Ejecutar el método que queremos probar)
        boolean resultadoReal = miModelo.registrarUsuario(usuario, cedula, contra);

        // 3. ASSERT (Verificar que el resultado es exactamente el esperado)
        // Esperamos que devuelva 'true' porque el registro debe ser exitoso
        assertEquals(true, resultadoReal, "El sistema debería retornar true al registrar un usuario válido");
    }

    @Test
    public void testVerificarLoginFallaConClaveMala() {
        // 1. ARRANGE (Preparar)
        modelo miModelo = new modelo();
        String usuarioExistente = "AAA_TestUser"; 
        String claveIncorrecta = "claveMala999";

        // 2. ACT (Actuar)
        boolean loginReal = miModelo.verificar(usuarioExistente, claveIncorrecta);

        // 3. ASSERT (Afirmar)
        // Esperamos que devuelva 'false' porque la clave no coincide
        assertEquals(false, loginReal, "El login debe fallar (false) si la contraseña es incorrecta");
    }

    // ==========================================
    // 2. PRUEBAS DEL SALDO DEL MONEDERO
    // ==========================================

    @Test
    public void testRecargarSaldoMontoPositivo() {
        // 1. ARRANGE (Preparar)
        modelo miModelo = new modelo();
        String usuario = "AAA_TestUser";
        double montoRecarga = 500.0;

        // 2. ACT (Actuar)
        boolean recargaExitosa = miModelo.recargarSaldo(usuario, montoRecarga);

        // 3. ASSERT (Afirmar)
        assertEquals(true, recargaExitosa, "La recarga de saldo debería retornar true al completarse");
    }

    @Test
    public void testConsultarSaldoUsuarioFantasma() {
        // 1. ARRANGE (Preparar)
        modelo miModelo = new modelo();
        String usuarioQueNoExiste = "Fantasma12345";
        String saldoEsperado = "0"; // Tu código devuelve "0" si no encuentra al usuario

        // 2. ACT (Actuar)
        String saldoReal = miModelo.Saldo(usuarioQueNoExiste);

        // 3. ASSERT (Afirmar)
        assertEquals(saldoEsperado, saldoReal, "Un usuario que no existe debe tener saldo 0");
    }
}