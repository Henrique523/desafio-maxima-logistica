import {Component, Input, OnInit} from '@angular/core';

@Component({
  selector: 'app-toolbar-button',
  templateUrl: './toolbar-button.component.html',
  styleUrls: ['./toolbar-button.component.css']
})
export class ToolbarButtonComponent implements OnInit {
  @Input() public path = '';
  @Input() public icon = '';
  @Input() public text = '';

  constructor() { }

  ngOnInit(): void {
  }

}
