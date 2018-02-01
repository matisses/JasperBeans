package co.matisses.jasperbeans;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author jguisao
 */
public class ProductoMercadolibreFactory {

    private static final Logger console = Logger.getLogger(ProductoMercadolibreFactory.class.getSimpleName());
    private Properties props = new Properties();

    public void cargarProperties() {
        props = new Properties();
        String serverConfUrl = System.getProperty("jboss.server.config.dir");
        console.log(Level.INFO, "Server config URL [{0}]", serverConfUrl);
        String propertiesFileName = "bcs.properties";
        String path = serverConfUrl + File.separator + propertiesFileName;
        console.log(Level.INFO, "Loading properties file: [{0}]", path);

        try {
            File propsFile = new File(path);
            if (propsFile.exists()) {
                props.load(new FileInputStream(propsFile));
            } else {
                props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("/" + propertiesFileName));
            }
        } catch (Exception e) {
            console.log(Level.SEVERE, "There was an error loading the file.", e);
        }
    }

    public String obtenerValorPropiedad(String prop) {
        return props.getProperty(prop);
    }

    public static Collection consultarDetalleCarga() {
        ProductoMercadolibreFactory factory = new ProductoMercadolibreFactory();
        factory.cargarProperties();

        ArrayList<ProductoMercadolibreBean> objetos = new ArrayList<ProductoMercadolibreBean>();
        try {
            File datos = new File(factory.obtenerValorPropiedad("url.log.mercadolibre"));
            BufferedReader br = new BufferedReader(new FileReader(datos));
            String datosLog = br.readLine();
            while (datosLog != null && datosLog.length() > 0) {
                String columnas[] = datosLog.split("\t");
                if (columnas.length == 3) {
                    objetos.add(new ProductoMercadolibreBean(columnas[0], columnas[1], columnas[2]));
                }
                datosLog = br.readLine();
            }
        } catch (Exception e) {
            console.log(Level.SEVERE, "Ocurrio un error al leer el archivo. ", e);
        }
        factory.props.clear();
        return objetos;
    }
}