package com.projetosuniso.digdin;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.projetosuniso.digdin.utils.CpfValidatorUtil;
import com.projetosuniso.digdin.utils.MaskEditUtil;

import org.w3c.dom.Text;

public class EntreContasCPFActivity extends Activity {

    boolean CPFvalid = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_entre_contas);

        final TextView CPFInvalido = findViewById(R.id.textCPFNaoEncontrado);
        final LinearLayout clienteInfo = findViewById(R.id.clientInfo);

        final EditText editTextCPF = findViewById(R.id.editTextCPFTRANSFERENCIA);

        final MediaPlayer clickButton = MediaPlayer.create(this, R.raw.button_click);

        Button backButton = findViewById(R.id.backButton);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButton.start();
                openTransferencia();
            }
        });

        Button transferirParaButton = findViewById(R.id.transferirParaButton);
        transferirParaButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickButton.start();
                openEntreContasValor();
            }
        });

        editTextCPF.addTextChangedListener(MaskEditUtil.mask(editTextCPF, MaskEditUtil.FORMAT_CPF));
        editTextCPF.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {



                if(!hasFocus) {
                    String CPF = editTextCPF.getText().toString();
                    String unmaskedCPF = MaskEditUtil.unmask(CPF);

                    if(CPF.matches("")) {
                        editTextCPF.setBackgroundResource(R.drawable.edittext_border);
                    }
                    else {

                        if(CpfValidatorUtil.isCPF(unmaskedCPF) == false) {
                            CPFvalid = false;
                            editTextCPF.setBackgroundResource(R.drawable.edittext_default);
                            editTextCPF.setBackgroundResource(R.drawable.edittext_border);
                            CPFInvalido.setVisibility(View.VISIBLE);
                            clienteInfo.setVisibility(View.INVISIBLE);
                        }
                        else {
                            CPFvalid = true;
                        }
                    }

                }
                else if(hasFocus) {
                    CPFvalid = false;
                    editTextCPF.setBackgroundResource(R.drawable.edittext_default);
                    CPFInvalido.setVisibility(View.INVISIBLE);
                }


            }
        });
        editTextCPF.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {

                String CPF = editTextCPF.getText().toString();

                if(CPF.length() == 14) {
                    InputMethodManager imm =  (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                    imm.hideSoftInputFromWindow(editTextCPF.getWindowToken(), 0);
                    editTextCPF.clearFocus();
                }

                if(CPFvalid) {
                    clienteInfo.setVisibility(View.VISIBLE);
                }
                else {
                    clienteInfo.setVisibility(View.INVISIBLE);
                }

            }
        });
    }

    public void openTransferencia() {
        Intent intent = new Intent(this, TransferenciaActivity.class);
        startActivity(intent);
    }

    public void openEntreContasValor() {
        Intent intent = new Intent(this, EntreContasValorActivity.class);
        startActivity(intent);
    }
}