import { ComponentFixture, TestBed } from '@angular/core/testing';

import { GuacComponent } from './guac.component';

describe('GuacComponent', () => {
  let component: GuacComponent;
  let fixture: ComponentFixture<GuacComponent>;

  beforeEach(() => {
    TestBed.configureTestingModule({
      declarations: [GuacComponent]
    });
    fixture = TestBed.createComponent(GuacComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
