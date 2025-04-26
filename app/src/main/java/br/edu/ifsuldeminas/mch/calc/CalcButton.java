package br.edu.ifsuldeminas.mch.calc;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CalcButton implements View.OnClickListener {

    private StringBuilder expressao;
    private TextView textResultado;
    private TextView textUltimoResultado;

    private Boolean flagResultado = false;

    public Boolean getFlagResultado() {
        return flagResultado;
    }

    public void setFlagResultado(Boolean flagResultado) {
        this.flagResultado = flagResultado;
    }

    public CalcButton(TextView textResultado, TextView textUltimoResultado, StringBuilder expressao) {
        this.textResultado = textResultado;
        this.textUltimoResultado = textUltimoResultado;
        this.expressao = expressao;
    }

    @Override
    public void onClick(View v) {
        Button button = (Button) v;
        if(flagResultado){
            textUltimoResultado.setText(textResultado.getText());
            expressao.setLength(0);
            expressao.append(textUltimoResultado.getText().toString());
            flagResultado = false;
            if(!(button.getText().toString().equals("X") || button.getText().toString().equals("%") || button.getText().toString().equals("+") || button.getText().toString().equals("-") || button.getText().toString().equals("÷"))){
                textResultado.setText("");
                textUltimoResultado.setText("");
                expressao.setLength(0);
            }
        }

        adicionarElementoExpressao(button.getText().toString());
        textResultado.setText(expressao);
    }

    private void adicionarElementoExpressao(String elemento){
        expressao.append(elemento);
    }
}
