package br.ulbra.folhapgto;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText nome, salarioBruto, numFilhos;
    Button calcular;
    RadioGroup rbsexo;
    TextView resultado;
    RadioButton m, f, o;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nome = findViewById(R.id.edNome);
        salarioBruto = findViewById(R.id.edSalBruto);
        rbsexo = findViewById(R.id.rdGrupo);
        numFilhos = findViewById(R.id.edNumFilhos);
        resultado = findViewById(R.id.txtResul);
        m = findViewById(R.id.masculino);
        f = findViewById(R.id.feminino);
        o = findViewById(R.id.outro);
        calcular = findViewById(R.id.btnCalcular);
        calcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                double novoSalario = 0, salarioFamilia = 0, INSS, IR;
                double salarioBrutoJava = Double.parseDouble(salarioBruto.getText().toString());

                if (salarioBrutoJava > 0) {
                    String tratamento;
                    int op = rbsexo.getCheckedRadioButtonId();
                    if (f.isChecked()) {
                        tratamento = "Senhora ";
                    } else if (m.isChecked()) {
                        tratamento = "Senhor ";
                    } else {
                        tratamento = "Prezadx ";
                    }

                    if (salarioBrutoJava <= 1212) {
                        salarioFamilia = Double.parseDouble(numFilhos.getText().toString()) * 56.47;
                        INSS = salarioBrutoJava * 0.075;
                    } else if (salarioBrutoJava < 2427.36) {
                        INSS = salarioBrutoJava * 0.09;
                    } else if (salarioBrutoJava < 3641.04) {
                        INSS = salarioBrutoJava * 0.12;
                    } else {
                        INSS = salarioBrutoJava * 0.14;
                    }
                    if (salarioBrutoJava <= 1903.98) {
                        IR = 0;
                    } else if (salarioBrutoJava >= 1903.99 && salarioBrutoJava <= 2826.65) {
                        IR = salarioBrutoJava * 0.075;
                    } else if (salarioBrutoJava <= 3751.05) {
                        IR = salarioBrutoJava * 0.15;
                    } else {
                        IR = salarioBrutoJava * 0.225;
                    }

                    novoSalario = salarioBrutoJava - INSS - IR + salarioFamilia;
                    resultado.setText(tratamento + nome.getText().toString() + "\nDescontos:" + "\nINSS: " + INSS
                            + "\nIR: " + IR + "\nSalário Família: " + salarioFamilia + "\nSalário Liquído: " + novoSalario);

                    nome.setText("");
                    salarioBruto.setText("");
                    numFilhos.setText("");
                } else
                    resultado.setText("Número negativo não aceito.");
            }
        });
    }
}