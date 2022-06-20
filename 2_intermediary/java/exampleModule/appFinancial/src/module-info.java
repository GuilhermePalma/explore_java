module appFinancial {

    // Package já importado que contem outros Pacotes Java (Ex: java.lang)
    requires java.base;

    // Utiliza a Classe "Calculate.java" desse Package
    requires appCalculate;
}