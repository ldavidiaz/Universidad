import 'package:flutter/material.dart';
import 'strings/strings_es.dart';

void main() {
  runApp(const MyApp());
}

class MyApp extends StatelessWidget {
  const MyApp({super.key});
  @override
  Widget build(BuildContext context) {
    return const MaterialApp(
      title: Strings.appTitle,
      home: LoginPage(),
    );
  }
}

class LoginPage extends StatefulWidget {
  const LoginPage({super.key});

  //const LoginPage({super.key});
  @override
  // ignore: library_private_types_in_public_api
  _LoginPageState createState() => _LoginPageState();
}

class _LoginPageState extends State<LoginPage> {
  final _formKey = GlobalKey<FormState>();
  bool _termsAccepted = false;
  final TextEditingController _usernameController = TextEditingController();
  final TextEditingController _passwordController = TextEditingController();

  void _showWelcomeDialog(String username) {
    showDialog(
      context: context,
      builder: (BuildContext context) {
        return AlertDialog(
          title: const Text(Strings.welcome),
          content: Text('Bienvenido, $username',style: const TextStyle(fontSize: 20)),
          actions: [
            TextButton(
              onPressed: () {
                Navigator.of(context).pop();
              },
              style: TextButton.styleFrom(
                backgroundColor: Colors.grey[300],
                foregroundColor: Colors.black,
                textStyle: const TextStyle(fontSize: 18),
              ),
              child: const Text(Strings.ok),
            ),
          ],
        );
      },
    );
  }

  @override
  Widget build(BuildContext context) {
    return Scaffold(
      /* appBar: AppBar(
        title: const Text(Strings.appTitle),
      ), */
      body: Padding(
        padding: const EdgeInsets.all(16.0),
        child: Form(
          key: _formKey,
          child: Column(
            children: [
              const FlutterLogo(size: 100),
              const Text(
                Strings.appTitle,
                style: TextStyle(fontSize: 24),
              ),
              SizedBox(
                height: MediaQuery.of(context).size.height * 0.25, // Ocupa el 30% de la pantalla
                child: Center(
                  child: Column(
                    mainAxisAlignment: MainAxisAlignment.center, // Centra verticalmente los campos
                    children: [
                      TextFormField(
                        controller: _usernameController,
                        decoration: const InputDecoration(labelText: Strings.username),
                        validator: (value) {
                          if (value == null || value.isEmpty) {
                            return 'Por favor ingrese su usuario';
                          }
                          return null;
                        },
                      ),
                      const SizedBox(height: 10), // Espacio entre los campos
                      TextFormField(
                        controller: _passwordController,
                        decoration: const InputDecoration(labelText: Strings.password),
                        obscureText: true,
                        validator: (value) {
                          if (value == null || value.isEmpty) {
                            return 'Por favor ingrese su contraseña';
                          }
                          return null;
                        },
                      ),
                    ],
                  ),
                ),
              ),

              const Expanded(
                child:  SingleChildScrollView(
                  child: Padding(
                    padding: EdgeInsets.only(top: 20.0),
                    child: Text(Strings.termsAndConditions),
                  ),
                ),
              ),
              Row(
                mainAxisAlignment: MainAxisAlignment.start,
                children: [
                  Switch(
                    value: _termsAccepted,
                    onChanged: (value) {
                      setState(() {
                        _termsAccepted = value;
                      });
                      
                    },
                    activeColor: Colors.blue,
                  ),
                  const Text(Strings.acceptTerms),
                ],
              ),

              SizedBox(
                width: double.infinity,
                child: ElevatedButton(
                  onPressed: _termsAccepted
                      ? () {
                          if (_formKey.currentState!.validate()) {
                            _showWelcomeDialog(_usernameController.text);
                          }
                        }
                      : null,
                  style: ElevatedButton.styleFrom(
                    backgroundColor: Colors.blue, // Color de fondo del botón
                    foregroundColor: Colors.white,// Color del texto del botón
                    shape: RoundedRectangleBorder(
                    borderRadius: BorderRadius.circular(1), // Poco redondeo
                    ), 
                  ),
                  child: const Text(Strings.login),
                ),
              ),
            ],
          ),
        ),
      ),
    );
  }
}
