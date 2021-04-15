package bandtec.adsa.fajan.app.continuada2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    var cachorro1: Cachorro? = null
    var cachorro2: Cachorro? = null

    var deuCerto1: Boolean? = null
    var deuCerto2: Boolean? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun comprar(view: View) {
        cachorro1 = null
        cachorro2 = null
        deuCerto1 = null
        deuCerto2 = null

        val apiCachorros = ConexaoApiCachorros.criar()

        val etIdCachorro1: EditText= findViewById(R.id.et_idCachorro1)
        val idCachorro1: Int = if (etIdCachorro1.text.toString() == "") 0 else etIdCachorro1.text.toString().toInt()

        val etIdCachorro2: EditText= findViewById(R.id.et_idCachorro2)
        val idCachorro2: Int = if (etIdCachorro2.text.toString() == "") 0 else etIdCachorro2.text.toString().toInt()

        apiCachorros.getById(idCachorro1).enqueue(object : Callback<Cachorro> {

            override fun onResponse(call: Call<Cachorro>, response: Response<Cachorro>) {
                if (response.code() == 200) {
                    deuCerto1 = true
                    cachorro1 = response.body()
                } else if (response.code() == 404) {
                    deuCerto1 = false
                }
                validarRespostas(idCachorro1, idCachorro2)
            }

            override fun onFailure(call: Call<Cachorro>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_SHORT).show()
            }
        })

        apiCachorros.getById(idCachorro2).enqueue(object : Callback<Cachorro> {

            override fun onResponse(call: Call<Cachorro>, response: Response<Cachorro>) {
                if (response.code() == 200) {
                    deuCerto2 = true
                    cachorro2 = response.body()
                } else if (response.code() == 404) {
                    deuCerto2 = false
                }
                validarRespostas(idCachorro1, idCachorro2)
            }

            override fun onFailure(call: Call<Cachorro>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }

    private fun validarRespostas(id1: Int, id2: Int) {
        if (deuCerto1 == null || deuCerto2 == null)
            return

        if (!deuCerto1!! && !deuCerto2!!) {
            var telaErro = Intent(this, MensagemDeErro::class.java)
            telaErro.putExtra("id1", id1)
            telaErro.putExtra("id2", id2)
            startActivity(telaErro)
        } else {
            val telaResultado = Intent(this, Resultado::class.java)
            telaResultado.putExtra("raca1", cachorro1?.raca)
            telaResultado.putExtra("raca2", cachorro2?.raca)
            val preco1 = cachorro1?.precoMedio
            val preco2 = cachorro2?.precoMedio
            val preco1Corrigido = if (preco1 != null) preco1 else 0.0
            val preco2Corrigido = if (preco2 != null) preco2 else 0.0
            val preco = preco1Corrigido + preco2Corrigido
            telaResultado.putExtra("precoTotal", preco)
            startActivity(telaResultado)
        }
    }
}