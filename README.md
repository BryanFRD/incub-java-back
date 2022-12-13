# ecom_back_end

##Requete public 'METHODE GET'
pour accéder aux requetes publiques depuis postman (methode GET)

    - http://localhost:8080/api/public/hello (permet d'accéder à une page de vérification)

    - http://localhost:8080/api/public/enr/users (permet d'obtenir la liste des utilisateurs dans la BDD)


##Requete privé 'METHODE GET'
pour accéder aux requetes privées depuis postman (methode GET avec le token)

    - http://localhost:8080/api/bye (permet d'accéder à une page privé de vérification)
il faut y inclure le token dans "Bearer" (pour récupérer le token voir juste en dessous)


##Requete pour Obtenir Token 'METHODE POST'
pour recevoir un token depuis postman (methode POST avec le JSON)
    - http://localhost:8080/api/public/login

il faut y ajouter au format JSON un Utilisateur existant dans la BDD (uniquement l'email et le mot de passe)
`{"email": "minou33@gmail.com",
  "password": "pass"
}`




## License
For open source projects, say how it is licensed.

## Project status
If you have run out of energy or time for your project, put a note at the top of the README saying that development has slowed down or stopped completely. Someone may choose to fork your project or volunteer to step in as a maintainer or owner, allowing your project to keep going. You can also make an explicit request for maintainers.
