import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SellerRegisterComponent } from './seller-register.component';

describe('SellerRegisterComponent', () => {
  let component: SellerRegisterComponent;
  let fixture: ComponentFixture<SellerRegisterComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SellerRegisterComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(SellerRegisterComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
