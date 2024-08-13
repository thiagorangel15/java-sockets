package control;

public class ValidadorCPF {

    public static String validarCPF(String cpf) {

        if (!cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}")) {
            return "Formato inválido";
        }

        String cpfNumeros = cpf.replaceAll("[^\\d]", "");


        if (cpfNumeros.matches("(\\d)\\1{10}")) {
            return "CPF inválido: dígitos repetidos";
        }
        return "CPF válido";
    }
}