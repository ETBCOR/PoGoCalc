package etbcor.pogocalculator;

import android.content.Intent;
import android.content.res.XmlResourceParser;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Xml;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Dictionary;
import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    private final String[] pokemon = {
            "Choose Pokémon",
            "Abra",
            "Bellsprout",
            "Bulbasaur",
            "Caterpie",
            "Charmander",
            "Charmeleon",
            "Clefairy",
            "Cubone",
            "Diglett",
            "Doduo",
            "Dragonair",
            "Dratini",
            "Drowzee",
            "Eevee-Flareon",
            "Eevee-Jolteon",
            "Eevee-Vaporeon",
            "Ekans",
            "Exeggcute",
            "Gastly",
            "Geodude",
            "Gloom",
            "Goldeen",
            "Graveler",
            "Grimer",
            "Growlithe",
            "Haunter",
            "Horsea",
            "Ivysaur",
            "Jigglypuff",
            "Kabuto",
            "Kadabra",
            "Kakuna",
            "Koffing",
            "Krabby",
            "Machoke",
            "Machop",
            "Magikarp",
            "Magnemite",
            "Mankey",
            "Meowth",
            "Metapod",
            "Nidoran F",
            "Nidoran M",
            "Nidorina",
            "Nidorino",
            "Oddish",
            "Omanyte",
            "Paras",
            "Pidgeotto",
            "Pidgey",
            "Pikachu",
            "Polywag",
            "Polywhirl",
            "Ponyta",
            "Psyduck",
            "Rattata",
            "Rhyhorn",
            "Sandshrew",
            "Seel",
            "Shellder",
            "Slowpoke",
            "Spearow",
            "Squirtle",
            "Staryu",
            "Tentacool",
            "Venonat",
            "Voltorb",
            "Vulpix",
            "Wartortle",
            "Weedle",
            "Weepinbell",
            "Zubat"
    };

    Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        InputStream data;
        try {
            data = this.getResources().openRawResource(R.raw.pokemon_stats);
            XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
            parser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES,false);
            parser.setInput(data, null);
            this.parseXml(parser);
        } catch (XmlPullParserException e) {
            e.printStackTrace();
        }

        spinner = (Spinner) findViewById(R.id.spinner);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, pokemon);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    private void parseXml(XmlPullParser p) {
        Map<String, String[]> d = new HashMap<>();
        int event, event2;
        String tagName, tagName2;
        try {
            event = p.getEventType();
            while (event != XmlResourceParser.END_DOCUMENT) {
                tagName = p.getName();
                switch (event) {
                    case XmlResourceParser.START_TAG:
                        if(tagName.equals("pokemon")) {
                            String pokemonName = p.getAttributeValue(null, "name");
                            Log.i("TAGGY!",pokemonName);
                        }
                        break;
                    case XmlResourceParser.TEXT:
                        break;
                    case XmlResourceParser.END_TAG:
                        break;
                }
                event = p.next();
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }
}
