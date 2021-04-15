package bandtec.adsa.fajan.app.continuada2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MensagemDeErro : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mensagem_de_erro)

        val id1 = intent.getIntExtra("id1", 0)
        val id2 = intent.getIntExtra("id2", 0)

        val tvMensagemErro: TextView = findViewById(R.id.tv_mensagemErro)
        var mensagemEditada = getString(R.string.mensagem_deu_ruim, id1, id2)
        tvMensagemErro.text = mensagemEditada
    }
}