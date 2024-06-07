import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ViewExamsComponent } from './view-exams.component';

describe('ViewExamsComponent', () => {
  let component: ViewExamsComponent;
  let fixture: ComponentFixture<ViewExamsComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ViewExamsComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ViewExamsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
