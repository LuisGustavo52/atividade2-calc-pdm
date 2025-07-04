package br.edu.ifsuldeminas.mch.calc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import de.congrace.exp4j.Calculable;
import de.congrace.exp4j.ExpressionBuilder;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "ifsuldeminas.mch.calc";
    private Button buttonIgual;
    private TextView textViewResultado;
    private TextView textViewUltimaExpressao;
    private Button buttonZero;
    private Button buttonUm;
    private Button buttonDois;
    private Button buttonTres;
    private Button buttonQuatro;
    private Button buttonCinco;
    private Button buttonSeis;
    private Button buttonSete;
    private Button buttonOito;
    private Button buttonNove;
    private Button buttonPorcento;
    private Button buttonAdicao;
    private Button buttonSubtracao;
    private Button buttonDivisao;
    private Button buttonMultiplicacao;
    private Button buttonSoma;
    private Button buttonVirgula;
    private Button buttonApagar;
    private Button buttonResetar;
    private StringBuilder expressaoResultado;

    private static boolean flagResultado = false;

    public static boolean isFlagResultado() {
        return flagResultado;
    }

    public static void setFlagResultado(boolean flagResultado) {
        MainActivity.flagResultado = flagResultado;
    }

    public static Boolean getIsOperatorAllowed() {
        return isOperatorAllowed;
    }

    public static void setIsOperatorAllowed(Boolean isOperatorAllowed) {
        MainActivity.isOperatorAllowed = isOperatorAllowed;
    }

    private static Boolean isOperatorAllowed = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        expressaoResultado = new StringBuilder();

        textViewResultado = findViewById(R.id.textViewResultadoID);
        textViewUltimaExpressao = findViewById(R.id.textViewUltimaExpressaoID);

        CalcButton calcButtonListener = new CalcButton(textViewResultado, textViewUltimaExpressao, expressaoResultado);
        CalcOperator calcOperatorListener = new CalcOperator(textViewResultado, textViewUltimaExpressao, expressaoResultado);

        buttonZero = findViewById(R.id.buttonZeroID);
        buttonZero.setOnClickListener(calcButtonListener);

        buttonUm = findViewById(R.id.buttonUmID);
        buttonUm.setOnClickListener(calcButtonListener);

        buttonDois = findViewById(R.id.buttonDoisID);
        buttonDois.setOnClickListener(calcButtonListener);

        buttonTres = findViewById(R.id.buttonTresID);
        buttonTres.setOnClickListener(calcButtonListener);

        buttonQuatro = findViewById(R.id.buttonQuatroID);
        buttonQuatro.setOnClickListener(calcButtonListener);

        buttonCinco = findViewById(R.id.buttonCincoID);
        buttonCinco.setOnClickListener(calcButtonListener);

        buttonSeis = findViewById(R.id.buttonSeisID);
        buttonSeis.setOnClickListener(calcButtonListener);

        buttonSete = findViewById(R.id.buttonSeteID);
        buttonSete.setOnClickListener(calcButtonListener);

        buttonOito = findViewById(R.id.buttonOitoID);
        buttonOito.setOnClickListener(calcButtonListener);

        buttonNove = findViewById(R.id.buttonNoveID);
        buttonNove.setOnClickListener(calcButtonListener);

        buttonPorcento = findViewById(R.id.buttonPorcentoID);
        buttonPorcento.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String expressaoAtual = textViewResultado.getText().toString();

                if(!isOperatorAllowed)
                    return;

                StringBuilder numero = new StringBuilder();
                for (int i = expressaoAtual.length() - 1; i >= 0; i--) {
                    char c = expressaoAtual.charAt(i);
                    if (Character.isDigit(c) || c == ',' || c == '.') {
                        numero.append(c);
                    } else {
                        break;
                    }
                }

                int start = expressaoAtual.length() - numero.length();
                String novaExpressao = expressaoAtual.substring(0, start) + "(" + numero + "/100)";
                expressaoResultado.setLength(0);
                expressaoResultado.append(novaExpressao);
                textViewResultado.setText(novaExpressao.toString());

                MainActivity.setIsOperatorAllowed(true);
            }
        });

        buttonSoma = findViewById(R.id.buttonSomaID);
        buttonSoma.setOnClickListener(calcOperatorListener);

        buttonSubtracao = findViewById(R.id.buttonSubtracaoID);
        buttonSubtracao.setOnClickListener(calcOperatorListener);

        buttonDivisao = findViewById(R.id.buttonDivisaoID);
        buttonDivisao.setOnClickListener(calcOperatorListener);

        buttonMultiplicacao = findViewById(R.id.buttonMultiplicacaoID);
        buttonMultiplicacao.setOnClickListener(calcOperatorListener);

        buttonVirgula = findViewById(R.id.buttonVirgulaID);
        buttonVirgula.setOnClickListener(calcOperatorListener);

        buttonIgual = findViewById(R.id.buttonIgualID);
        buttonIgual.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calculable avaliadorExpressao = null;
                String expressaoCorrigida = getStringCorrigida(expressaoResultado);
                try {
                    avaliadorExpressao = new ExpressionBuilder(expressaoCorrigida).build();
                    Double resultado = avaliadorExpressao.calculate();
                    setFlagResultado(true);

                    textViewUltimaExpressao.setText(expressaoResultado.toString());
                    textViewResultado.setText(resultado.toString().replace(".", ","));
                } catch (Exception e) {
                    Log.d(TAG, e.getMessage());
                }

            }
        });

        buttonResetar = findViewById(R.id.buttonResetID);
        buttonResetar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textViewUltimaExpressao.setText("");
                textViewResultado.setText("");
                expressaoResultado.setLength(0);
                textViewResultado.setText("0");
            }
        });

        buttonResetar = findViewById(R.id.buttonResetID);
        buttonResetar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                textViewUltimaExpressao.setText("");
                textViewResultado.setText("");
                expressaoResultado.setLength(0);
                textViewResultado.setText("0");
                setFlagResultado(false);
            }
        });

        buttonApagar = findViewById(R.id.buttonDeleteID);
        buttonApagar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (textViewResultado.getText().length() == 1 || textViewResultado.getText() == null || textViewResultado.getText().toString().equals("0")) {
                    textViewResultado.setText("0");
                    expressaoResultado.setLength(0);
                } else {
                    String stringApagada = textViewResultado.getText().toString();
                    stringApagada = stringApagada.substring(0, stringApagada.length() - 1).toString();
                    textViewResultado.setText(stringApagada);
                    expressaoResultado.setLength(0);
                    expressaoResultado.append(stringApagada);
                }
            }
        });
    }

    @NonNull
    private String getStringCorrigida(StringBuilder expressaoResultado) {
        String expressaoCorrigida = expressaoResultado.toString().replace(",", ".");
        expressaoCorrigida = expressaoCorrigida.replace("÷", "/");
        expressaoCorrigida = expressaoCorrigida.replace("X", "*");
        return expressaoCorrigida;
    }

}