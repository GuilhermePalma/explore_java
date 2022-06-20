module appCalculate {
    // Exporta apenas as Classes que estão no "appCalculate", mas os outros Packages são mantidos de fora
    exports com.guilhermepalma.appCalculate;

    // Exporta as Classes Internas para o "appFinancial"
    exports com.guilhermepalma.appCalculate.intern to appFinancial;

    // Utiliza a Classe "Log.java" desse Package
    requires transitive appLog;
}