package br.edu.ifsuldeminas.mch.calc;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CalcButton implements View.OnClickListener {

    private StringBuilder expressao;
    private TextView textResultado;
    private TextView textUltimoResultado;

    public CalcButton(TextView textResultado, TextView textUltimoResultado, StringBuilder expressao) {
        this.textResultado = textResultado;
        this.textUltimoResultado = textUltimoResultado;
        this.expressao = expressao;
    }

    @Override
    public void onClick(View v) {
        Button button = (Button) v;
        if(MainActivity.isFlagResultado()){
            textResultado.setText("");
            textUltimoResultado.setText("");
            expressao.setLength(0);
            MainActivity.setFlagResultado(false);
        }

        adicionarElementoExpressao(button.getText().toString());
        textResultado.setText(expressao);
        MainActivity.setIsOperatorAllowed(true);
    }

    private void adicionarElementoExpressao(String elemento){
        expressao.append(elemento);
    }

}
