package com.example.folhapagamento;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText edtnomefun, edtsalario, edtfilhos;

    RadioButton rbmasculino, rbfeminino;

    Button btncalcular;

    TextView txtresultado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        edtnomefun = findViewById(R.id.edtnomefun);

        edtsalario = findViewById(R.id.edtsalario);

        edtfilhos = findViewById(R.id.edtfilhos);

        rbmasculino = findViewById(R.id.rbmasculino);

        rbfeminino = findViewById(R.id.rbfeminino);

        btncalcular = findViewById(R.id.btncalcular);

        txtresultado = findViewById(R.id.txtresultado);

        btncalcular.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                calcularFolha();
            }
        });
    }

    private void calcularFolha() {

        try {

            String nome = edtnomefun.getText().toString();

            double salario = Double.parseDouble(edtsalario.getText().toString());

            int filhos = Integer.parseInt(edtfilhos.getText().toString());

            if (nome.isEmpty()) {

                Toast.makeText(MainActivity.this, "Digite o nome", Toast.LENGTH_SHORT).show();

                return;
            }

            if (salario < 0 || filhos < 0) {

                Toast.makeText(MainActivity.this, "Valores inválidos", Toast.LENGTH_SHORT).show();

                return;
            }

            String tratamento;

            if (rbmasculino.isChecked()) {

                tratamento = "Sr.";

            } else {

                tratamento = "Sra.";
            }

            double inss = 0;

            if (salario <= 1212.00) {

                inss = salario * 0.075;

            } else if (salario <= 2427.35) {

                inss = salario * 0.09;

            } else if (salario <= 3641.03) {

                inss = salario * 0.12;

            } else {

                inss = salario * 0.14;
            }

            double ir = 0;

            if (salario <= 1903.98) {

                ir = 0;

            } else if (salario <= 2826.65) {

                ir = salario * 0.075;

            } else if (salario <= 3751.05) {

                ir = salario * 0.15;

            } else if (salario <= 4664.68) {

                ir = salario * 0.225;
            }

            double salarioFamilia = 0;

            if (salario <= 1212.00) {

                salarioFamilia =
                        filhos * 56.47;
            }

            double salarioLiquido =
                    salario - (inss + ir)
                            + salarioFamilia;

            txtresultado.setText(tratamento + " " + nome + "\n\nINSS: R$ " + String.format("%.2f", inss) + "\nIR: R$ " + String.format("%.2f", ir) + "\nSalário Líquido: R$ " + String.format("%.2f", salarioLiquido)

            );

        } catch (Exception e) {

            Toast.makeText(MainActivity.this, "Preencha os campos corretamente", Toast.LENGTH_SHORT).show();
        }
    }
}