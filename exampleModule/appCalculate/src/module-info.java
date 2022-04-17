module appCalculate {
    // Exporta apenas as Classes que estão no "appCalculate", mas os outros Packages são mantidos de fora
    exports com.guilhermepalma.appCalculate;

    // Utiliza a Classe "Log.java" desse Package
    requires transitive appLog;
}