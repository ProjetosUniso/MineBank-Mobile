package com.projetosuniso.digdin;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.projetosuniso.digdin.utils.EmailValidatorUtil;

public class CadastroEmailActivity extends Activity {

    boolean EMAILvalid = false;
    boolean CONFIRMEMAILvalid = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastroemail);

        final MediaPlayer clickButton = MediaPlayer.create(this, R.raw.button_click);

        final ImageView checkEMAIL = findViewById(R.id.imageCheckEMAIL);
        final ImageView checkCONFIRMEMAIL =findViewById(R.id.imageCheckCONFIRMEMAIL);

        final ImageView wrongEMAIL = findViewById(R.id.imageWrongEMAIL);
        final ImageView wrongCONFIRMEMAIL = findViewById(R.id.imageWrongCONFIRMEMAIL);

        final EditText editTextEMAIL = findViewById(R.id.editTextEMAIL);
        final EditText editTextCONFIRMEMAIL = findViewById(R.id.editTextCONFIRMEMAIL);

        final TextView textEmailInvalido = findViewById(R.id.textEmailInvalido);
        final TextView textEmailsDiferentes = findViewById(R.id.textEmailsDiferentes);

        Button voltarButton = findViewById(R.id.voltarButton);
        voltarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButton.start();
                openLogin();
            }
        });

        Button continuarButton = findViewById(R.id.continuarButton);
        continuarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButton.start();
                openCadastroInfoPessoal();
            }
        });

        editTextEMAIL.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {

                String EMAIL = editTextEMAIL.getText().toString();

                if(!hasFocus) {
                    if(EmailValidatorUtil.isValidEmailAddressRegex(EMAIL) == false) {
                        editTextEMAIL.setBackgroundResource(R.drawable.edittext_border);
                        textEmailInvalido.setVisibility(View.VISIBLE);
                        checkEMAIL.setVisibility(View.INVISIBLE);
                        wrongEMAIL.setVisibility(View.VISIBLE);
                        EMAILvalid = false;
                    }
                    else {
                        editTextEMAIL.setBackgroundResource(R.drawable.edittext_default);
                        checkEMAIL.setVisibility(View.VISIBLE);
                        EMAILvalid = true;
                    }
                }
                else if (hasFocus) {
                    editTextEMAIL.setBackgroundResource(R.drawable.edittext_default);
                    textEmailInvalido.setVisibility(View.INVISIBLE);
                    wrongEMAIL.setVisibility(View.INVISIBLE);
                }
            }
        });

        editTextCONFIRMEMAIL.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {

                String EMAIL = editTextEMAIL.getText().toString();
                String CONFIRMEMAIL = editTextCONFIRMEMAIL.getText().toString();

                if(!hasFocus) {
                    if(EMAILvalid == true) {
                        if(!EMAIL.equals(CONFIRMEMAIL)) {
                            editTextCONFIRMEMAIL.setBackgroundResource(R.drawable.edittext_border);
                            textEmailsDiferentes.setVisibility(View.VISIBLE);
                            checkCONFIRMEMAIL.setVisibility(View.INVISIBLE);
                            wrongCONFIRMEMAIL.setVisibility(View.VISIBLE);
                            CONFIRMEMAILvalid = false;
                        }
                        else {
                            editTextCONFIRMEMAIL.setBackgroundResource(R.drawable.edittext_default);
                            textEmailsDiferentes.setVisibility(View.INVISIBLE);
                            checkCONFIRMEMAIL.setVisibility(View.VISIBLE);
                            CONFIRMEMAILvalid = true;
                        }
                    }
                    else {
                        editTextCONFIRMEMAIL.setBackgroundResource(R.drawable.edittext_border);
                        checkCONFIRMEMAIL.setVisibility(View.INVISIBLE);
                        wrongCONFIRMEMAIL.setVisibility(View.VISIBLE);
                    }
                }
                else if (hasFocus) {
                    editTextCONFIRMEMAIL.setBackgroundResource(R.drawable.edittext_default);
                    textEmailsDiferentes.setVisibility(View.INVISIBLE);
                    wrongCONFIRMEMAIL.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    public void openLogin() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

    public void openCadastroInfoPessoal() {
        EditText editTextEMAIL = findViewById(R.id.editTextEMAIL);
        EditText editTextCONFIRMEMAIL = findViewById(R.id.editTextCONFIRMEMAIL);

        editTextEMAIL.clearFocus();
        editTextCONFIRMEMAIL.clearFocus();

        if(EMAILvalid == true && CONFIRMEMAILvalid == true) {
            Intent intent = new Intent(this, CadastroInfoPessoalActivity.class);
            startActivity((intent));
        }
    }
}