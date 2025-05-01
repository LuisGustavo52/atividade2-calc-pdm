package br.edu.ifsuldeminas.mch.calc;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CalcOperator implements View.OnClickListener {

    private StringBuilder expressao;
    private TextView textResultado;
    private TextView textUltimoResultado;


    public CalcOperator(TextView textResultado, TextView textUltimoResultado, StringBuilder expressao) {
        this.textResultado = textResultado;
        this.textUltimoResultado = textUltimoResultado;
        this.expressao = expressao;
    }

    @Override
    public void onClick(View v) {
        if(MainActivity.getIsOperatorAllowed()){
            Button button = (Button) v;
            if(MainActivity.isFlagResultado()){
                expressao.setLength(0);
                expressao.append(textResultado.getText().toString());
                textUltimoResultado.setText(textResultado.getText());
                MainActivity.setFlagResultado(false);
            }

            adicionarElementoExpressao(button.getText().toString());
            textResultado.setText(expressao);
            MainActivity.setIsOperatorAllowed(false);
        }
    }

    private void adicionarElementoExpressao(String elemento){
        expressao.append(elemento);
    }

    public boolean isOperatorAllowed() {
        char ultimoCaracter = textResultado.getText().toString().charAt(textResultado.getText().length() - 1);

        if (textResultado.getText() == null || textResultado.getText().toString().equals("0"))
            return false;
        return !"+-X÷%, ".contains(String.valueOf(ultimoCaracter));
    }
}
