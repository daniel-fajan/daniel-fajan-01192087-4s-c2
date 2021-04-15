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

    var deuCerto1: Boolean = false
    var deuCerto2: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun comprar(view: View) {
        val apiCachorros = ConexaoApiCachorros.criar()

        val etIdCachorro1: EditText= findViewById(R.id.et_idCachorro1)
        val idCachorro1: Int = if (etIdCachorro1.text.toString() == "") 0 else etIdCachorro1.text.toString().toInt()

        apiCachorros.getById(idCachorro1).enqueue(object : Callback<Cachorro> {

            override fun onResponse(call: Call<Cachorro>, response: Response<Cachorro>) {
                if (response.code() == 200) {
                    deuCerto1 = true
                    cachorro1 = response.body()
                } else if (response.code() == 404) {
                    deuCerto1 = false
                }
            }

            override fun onFailure(call: Call<Cachorro>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_SHORT).show()
            }
        })

        val etIdCachorro2: EditText= findViewById(R.id.et_idCachorro2)
        val idCachorro2: Int = if (etIdCachorro2.text.toString() == "") 0 else etIdCachorro2.text.toString().toInt()

        apiCachorros.getById(idCachorro2).enqueue(object : Callback<Cachorro> {

            override fun onResponse(call: Call<Cachorro>, response: Response<Cachorro>) {
                if (response.code() == 200) {
                    deuCerto2 = true
                    cachorro2 = response.body()
                } else if (response.code() == 404) {
                    deuCerto2 = false
                }
            }

            override fun onFailure(call: Call<Cachorro>, t: Throwable) {
                Toast.makeText(baseContext, t.message, Toast.LENGTH_SHORT).show()
            }
        })

        validarRespostas(idCachorro1, idCachorro2)
    }

    private fun validarRespostas(id1: Int, id2: Int) {
        if (!deuCerto1 && !deuCerto2) {
            var telaErro = Intent(this, MensagemDeErro::class.java)
            telaErro.putExtra("id1", id1)
            telaErro.putExtra("id2", id2)
            startActivity(telaErro)
        } else {
            val telaResultado = Intent(this, Resultado::class.java)
            telaResultado.putExtra("raca1", cachorro1?.raca)
            telaResultado.putExtra("raca2", cachorro2?.raca)
            val preco = cachorro1?.precoMedio?.plus(cachorro2?.precoMedio!!)
            telaResultado.putExtra("precoTotal", preco)
            startActivity(telaResultado)
        }
    }
}