package clientes;

public enum EnumTipoCliente {
    MAYORISTA {
        @Override
        public String toString() {
            return "Mayorista";
        }
    },
    MINORISTA {
        @Override
        public String toString() {
            return "Minorista";
        }
    };
    
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
