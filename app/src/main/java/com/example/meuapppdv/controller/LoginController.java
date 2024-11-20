package com.example.meuapppdv.controller;

import android.content.Context;

import com.example.meuapppdv.dao.LoginDao;
import com.example.meuapppdv.model.Login;

public class LoginController {
    private Context context;
    public LoginController(Context context) {this.context = context;}

    public String salvarLogin(String nome, String usuario, String senha) {
        try {
            if (senha.equals("") || senha.isEmpty()) {
                return "Informe um Nome!";
            }
            if (usuario.equals("") || usuario.isEmpty()) {
                return "Informe o Usuario!";
            }
            if (senha.equals("") || senha.isEmpty()) {
                return "Informe uma Senha!";
            }

            Login login = new Login();

            login.setNome(nome);
            login.setUsuario(usuario);
            login.setSenha(senha);

            LoginDao.getInstancia(context).insert(login);

        } catch (Exception ex) {
            return "Erro ao gravar Usuario." + ex.getMessage();
        }
        return null;
    }

    public Login validarLogin(String usuario, String senha) {
        try {

            if (usuario.equals("") || usuario.isEmpty()) {

            }
            if (senha.equals("") || senha.isEmpty()) {


            }
            Login login = LoginDao.getInstancia(context).getByUser(usuario);
            return login;

        } catch (Exception ex) {
            //  "Erro ao encontrar Usuario."+ex.getMessage();}
        }
        /** public ArrayList<Login> retornarLogin(){
         return LoginDao.getInstancia(context).getAll();
         }**/
        return null;
    }
}
