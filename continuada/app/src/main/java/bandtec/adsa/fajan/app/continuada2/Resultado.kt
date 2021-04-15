package bandtec.adsa.fajan.app.continuada2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class Resultado : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resultado)

        val raca1 = intent.getStringExtra("raca1")
        val raca2 = intent.getStringExtra("raca2")
        val precoTotal = intent.getDoubleExtra("precoTotal", 0.0)

        val tvCachorro1: TextView = findViewById(R.id.tv_cachorro1)
        if (raca1 != null && raca1 != "") {
            tvCachorro1.text = getString(R.string.cachorro_1, raca1)
        } else {
            tvCachorro1.text = getString(R.string.cachorro_1, "Não encontrado")
        }

        val tvCachorro2: TextView = findViewById(R.id.tv_cachorro2)
        if (raca2 != null && raca2 != "") {
            tvCachorro2.text = getString(R.string.cachorro_2, raca2)
        } else {
            tvCachorro2.text = getString(R.string.cachorro_2, "Não encontrado")
        }

        val tvPrecoTotal: TextView = findViewById(R.id.tv_precoTotal)
        tvPrecoTotal.text = getString(R.string.preco_total, precoTotal)
    }
}