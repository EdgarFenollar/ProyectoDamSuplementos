package clientes;

public enum EnumTipoCliente {
    MAYORISTA, MINORISTA;

    public static boolean validarTipoCliente(String tipo){
        EnumTipoCliente[]tipoClientes = EnumTipoCliente.values();
        for (int i = 0; i < tipoClientes.length; i++) {
            if (tipoClientes[i].toString().equals(tipo)){
                return true;
            }
        }
        return false;
    }
}
