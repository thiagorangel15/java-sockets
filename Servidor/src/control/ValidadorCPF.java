package control;

public class ValidadorCPF {

    public static String validarCPF(String cpf) {
        // Verifica se o CPF está no formato correto XXX.XXX.XXX-XX
        if (!cpf.matches("\\d{3}\\.\\d{3}\\.\\d{3}-\\d{2}")) {
            return "Formato inválido";
        }

        // Remove os caracteres especiais (pontos e hífen)
        String cpfNumeros = cpf.replaceAll("[^\\d]", "");

        // Verifica se todos os dígitos são iguais (caso comum de CPF inválido)
        if (cpfNumeros.matches("(\\d)\\1{10}")) {
            return "CPF inválido: dígitos repetidos";
        }

        // Calcula os dígitos verificadores
        if (!calcularDigitoVerificador(cpfNumeros)) {
            return "CPF inválido: dígitos verificadores não correspondem";
        }

        return "CPF válido";
    }

    private static boolean calcularDigitoVerificador(String cpf) {
        int soma = 0;
        int peso = 10;
        for (int i = 0; i < 9; i++) {
            soma += (cpf.charAt(i) - '0') * peso--;
        }

        int primeiroDigitoVerificador = 11 - (soma % 11);
        if (primeiroDigitoVerificador >= 10) {
            primeiroDigitoVerificador = 0;
        }

        soma = 0;
        peso = 11;
        for (int i = 0; i < 10; i++) {
            soma += (cpf.charAt(i) - '0') * peso--;
        }

        int segundoDigitoVerificador = 11 - (soma % 11);
        if (segundoDigitoVerificador >= 10) {
            segundoDigitoVerificador = 0;
        }

        return cpf.charAt(9) - '0' == primeiroDigitoVerificador &&
                cpf.charAt(10) - '0' == segundoDigitoVerificador;
    }

    public static void main(String[] args) {
        String cpf = "123.456.789-09";
        String resultado = validarCPF(cpf);
        System.out.println("Resultado da validação: " + resultado);
    }
}
