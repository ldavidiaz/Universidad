package fonts;

import java.awt.Font;
import java.io.InputStream;

public class Fuente {
    private Font font = null;
    public String MINECRAFT = "MinecraftTen-VGORe.ttf";
    public String MINECRAFT_REGULAR = "MinecraftRegular.otf";
    public String MINECRAFT_BOLD = "MinecraftBold.otf";
    
    /* Font.PLAIN = 0 , Font.BOLD = 1 , Font.ITALIC = 2
	 * tamanio = float
	 */
    public Font fuente( String fontName, int estilo, float tamanio)
    {
         try {
            //Se carga la fuente
            InputStream is =  getClass().getResourceAsStream(fontName);
            font = Font.createFont(Font.TRUETYPE_FONT, is);
        } catch (Exception ex) {
            //Si existe un error se carga fuente por defecto ARIAL
            System.err.println(fontName + " No se cargo la fuente");
            font = new Font("Arial", Font.PLAIN, 14);            
        }
        Font tfont = font.deriveFont(estilo, tamanio);
        return tfont;
    }
}
