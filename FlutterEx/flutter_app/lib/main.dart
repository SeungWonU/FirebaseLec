// import 'package:flutter/material.dart';
//
// class _MyFormState extends State<MyForm>{
//   TextEditingController textEditingController = TextEditingController();
//   String displayedText = "Hello, Seungwon's Flutter";
//
//   Widget build(BuildContext context) {
//     return Center(
//       child: Column(
//         mainAxisAlignment: MainAxisAlignment.center,
//         children: [Text(displayedText),
//         TextField(controller: textEditingController),
//         RaisedButton(child: Text("Change text!!!"),
//             onPressed: () => setState((){
//               displayedText = textEditingController.text;
//             }),
//         ),
//         ],
//       ));
//   }
// }
// class MyForm extends StatefulWidget{
//   _MyFormState createState() => _MyFormState();
// }
//
// class MyApp extends StatelessWidget{
//   Widget build(BuildContext context){
//     return MaterialApp(
//       title: 'Button and TextFiled test!!!',
//       home:
//         Scaffold(appBar: AppBar(title: Text('AppBar Title')),body:MyForm()),
//     );
//   }
// }
// void main() => runApp(MyApp());
import 'package:flutter/material.dart';
import 'package:provider/provider.dart';
import 'package:flutter_app/pages/start_page.dart';
import 'package:flutter_app/pages/image_page.dart';
import 'package:flutter_app/pages/change_name.dart';
import 'package:flutter_app/pages/form_page.dart';
import 'package:flutter_app/pages/list_page.dart';
import 'package:flutter_app/app_state.dart';



class MyApp extends StatelessWidget {
  @override
  Widget build(BuildContext context) {
    return MaterialApp(
      title: 'Navigation Test',
      initialRoute: StartPage.nav_url,
      routes: {
        StartPage.nav_url: (context) => StartPage(),
        ChangeNamePage.nav_url: (context) => ChangeNamePage(),
        FormPage.nav_url: (context) => FormPage(),
        ImagePage.nav_url: (context) => ImagePage(),
        ListPage.nav_url: (context) => ListPage(),
      },
    );
  }
}

void main() => runApp(ChangeNotifierProvider(
  // MyApp() 포함하여 자손 위젯들에게 AppState()를 제공
    create: (context) => AppState(),
    child: MyApp()));




























//
// void main() {
//   runApp(MyApp());
// }
//
// class MyApp extends StatelessWidget {
//   // This widget is the root of your application.
//   @override
//   Widget build(BuildContext context) {
//     return MaterialApp(
//       title: 'Flutter Demo',
//       theme: ThemeData(
//         // This is the theme of your application.
//         //
//         // Try running your application with "flutter run". You'll see the
//         // application has a blue toolbar. Then, without quitting the app, try
//         // changing the primarySwatch below to Colors.green and then invoke
//         // "hot reload" (press "r" in the console where you ran "flutter run",
//         // or simply save your changes to "hot reload" in a Flutter IDE).
//         // Notice that the counter didn't reset back to zero; the application
//         // is not restarted.
//         primarySwatch: Colors.blue,
//         // This makes the visual density adapt to the platform that you run
//         // the app on. For desktop platforms, the controls will be smaller and
//         // closer together (more dense) than on mobile platforms.
//         visualDensity: VisualDensity.adaptivePlatformDensity,
//       ),
//       home: MyHomePage(title: 'Hello Flutter Demo Home Page'),
//     );
//   }
// }
//
// class MyHomePage extends StatefulWidget {
//   MyHomePage({Key key, this.title}) : super(key: key);
//
//   // This widget is the home page of your application. It is stateful, meaning
//   // that it has a State object (defined below) that contains fields that affect
//   // how it looks.
//
//   // This class is the configuration for the state. It holds the values (in this
//   // case the title) provided by the parent (in this case the App widget) and
//   // used by the build method of the State. Fields in a Widget subclass are
//   // always marked "final".
//
//
//   final String title;
//
//   @override
//   _MyHomePageState createState() => _MyHomePageState();
// }
//
// class _MyHomePageState extends State<MyHomePage> {
//   int _counter = 0;
//
//   void _incrementCounter() {
//     setState(() {
//       // This call to setState tells the Flutter framework that something has
//       // changed in this State, which causes it to rerun the build method below
//       // so that the display can reflect the updated values. If we changed
//       // _counter without calling setState(), then the build method would not be
//       // called again, and so nothing would appear to happen.
//       _counter++;
//     });
//   }
//
//   @override
//   Widget build(BuildContext context) {
//     // This method is rerun every time setState is called, for instance as done
//     // by the _incrementCounter method above.
//     //
//     // The Flutter framework has been optimized to make rerunning build methods
//     // fast, so that you can just rebuild anything that needs updating rather
//     // than having to individually change instances of widgets.
//     return Scaffold(
//       appBar: AppBar(
//         // Here we take the value from the MyHomePage object that was created by
//         // the App.build method, and use it to set our appbar title.
//         title: Text(widget.title),
//       ),
//       body: Center(
//         // Center is a layout widget. It takes a single child and positions it
//         // in the middle of the parent.
//         child: Column(
//           // Column is also a layout widget. It takes a list of children and
//           // arranges them vertically. By default, it sizes itself to fit its
//           // children horizontally, and tries to be as tall as its parent.
//           //
//           // Invoke "debug painting" (press "p" in the console, choose the
//           // "Toggle Debug Paint" action from the Flutter Inspector in Android
//           // Studio, or the "Toggle Debug Paint" command in Visual Studio Code)
//           // to see the wireframe for each widget.
//           //
//           // Column has various properties to control how it sizes itself and
//           // how it positions its children. Here we use mainAxisAlignment to
//           // center the children vertically; the main axis here is the vertical
//           // axis because Columns are vertical (the cross axis would be
//           // horizontal).
//           mainAxisAlignment: MainAxisAlignment.center,
//           children: <Widget>[
//             Text(
//               'You have pushed the button this many times:',
//             ),
//             Text(
//               '$_counter',
//               style: Theme.of(context).textTheme.headline4,
//             ),
//           ],
//         ),
//       ),
//       floatingActionButton: FloatingActionButton(
//         onPressed: _incrementCounter,
//         tooltip: 'Increment',
//         child: Icon(Icons.add),
//       ), // This trailing comma makes auto-formatting nicer for build methods.
//     );
//   }
// }