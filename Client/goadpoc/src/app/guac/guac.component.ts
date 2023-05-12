import {Component, OnInit} from '@angular/core';

// @ts-ignore
import Guacamole from 'guacamole-common-js';
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-guac',
  templateUrl: './guac.component.html',
  styleUrls: ['./guac.component.css']
})
export class GuacComponent implements OnInit {
  title = 'goadpoc';

  constructor(private route: ActivatedRoute) {}

  ngOnInit() {
// Get display div from document
    // Get display div from document
    var host = "";
    var protocol = "";

    this.route.queryParams.subscribe(params => {
      host = params['host'];
      protocol = params['protocol'];
    });

    console.log("host", host);

    var display = document.getElementById("display");

    var tunnel = new Guacamole.HTTPTunnel("http://localhost:8080/guacamole-example-1.5.1/tunnel", true, {
      "hostname": host,
      "protocol": protocol
    });

    var client = new Guacamole.Client(tunnel);

    // Error handler
    client.onerror = function (error: any) {
      alert(error);
    };

    console.log("client", client);

    console.log("tunnel", tunnel);

    console.log("protocol", protocol)

    if (display == null) {
      return;
    }

    // Add client to display div
    display.appendChild(client.getDisplay().getElement());
    display.style.maxWidth = "100vw";
    display.style.maxHeight = "100vh";

    client.connect();

    // Mouse
    var mouse = new Guacamole.Mouse(client.getDisplay().getElement());

    mouse.onmousedown =
      mouse.onmouseup   =
        mouse.onmousemove = function(mouseState: any) {
          client.sendMouseState(mouseState);
        };

    // Keyboard
    var keyboard = new Guacamole.Keyboard(document);

    keyboard.onkeydown = function (keysym: any) {
      client.sendKeyEvent(1, keysym);
    };

    keyboard.onkeyup = function (keysym: any) {
      client.sendKeyEvent(0, keysym);
    };

    window.onunload = function () {
      client.disconnect();
    }
  }
}
