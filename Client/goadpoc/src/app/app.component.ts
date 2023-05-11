import {Component, OnInit} from '@angular/core';

//@ts-ignore
import Guacamole from 'guacamole-common-js';



@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  title = 'goadpoc';



  ngOnInit() {
// Get display div from document
    // Get display div from document
    var display = document.getElementById("display");

    var tunnel = new Guacamole.HTTPTunnel("http://localhost:8080/guac/tunnel");

    var client = new Guacamole.Client(tunnel);

    // Error handler
    client.onerror = function (error: any) {
      alert(error);
    };

    console.log("client", client);

    console.log("tunnel", tunnel);

    if (display == null) return;

    // Add client to display div
    document.body.appendChild(client.getDisplay().getElement());



    client.connect();

    window.onunload = function () {
      client.disconnect();
    }
  }
}
