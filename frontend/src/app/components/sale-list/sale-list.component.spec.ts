import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SaleListComponent } from './sale-list.component';

describe('SaleListComponent', () => {
  let component: SaleListComponent;
  let fixture: ComponentFixture<SaleListComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SaleListComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(SaleListComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
