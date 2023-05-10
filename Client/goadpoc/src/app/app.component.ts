import {Component, OnInit} from '@angular/core';

//@ts-ignore
import { default as Guacamole } from 'guacamole-common-js';



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

    // Instantiate client, using an HTTP tunnel for communications.
    var guac = new Guacamole.Client(
      new Guacamole.HTTPTunnel("http://localhost:8080/guac/connect")
    );

    if (display == null) return;

    // Add client to display div
    display.appendChild(guac.getDisplay().getElement());

    // Error handler
    guac.onerror = function(error:any) {
      alert(error);
    };

    // Connect
    guac.connect();

    // Disconnect on close
    window.onunload = function() {
      guac.disconnect();
    }
  }
}
