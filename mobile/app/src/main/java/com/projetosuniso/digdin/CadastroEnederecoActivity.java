package com.projetosuniso.digdin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.projetosuniso.digdin.utils.MaskEditUtil;

public class CadastroEnederecoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_enedereco);

        Button voltarButton = findViewById(R.id.voltarButton);
        voltarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCadastroInfoPessoal();
            }
        });

        Button continuarButton = findViewById(R.id.continuarButton);
        continuarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCadastroSenha();
            }
        });

        EditText editTextCEP = findViewById(R.id.editTextCEP);
        editTextCEP.addTextChangedListener(MaskEditUtil.mask(editTextCEP, MaskEditUtil.FORMAT_CEP));
    }

    public void openCadastroInfoPessoal() {
        Intent intent = new Intent(this, CadastroEmailActivity.class);
        startActivity((intent));
    }

    public void openCadastroSenha() {
        Intent intent = new Intent(this, CadastroSenhaActivity.class);
        startActivity((intent));
    }
}