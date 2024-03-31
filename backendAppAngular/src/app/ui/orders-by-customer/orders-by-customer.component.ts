import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {HttpClient} from "@angular/common/http";

@Component({
  selector: 'app-orders-by-customer',
  templateUrl: './orders-by-customer.component.html',
  styleUrl: './orders-by-customer.component.css'
})
export class OrdersByCustomerComponent implements OnInit{
  customerId : any;
  customerDetails : any;
  constructor(private route:ActivatedRoute, private http:HttpClient,private router:Router) {
    this.customerId = this.route.snapshot.params['id'];
  }

  ngOnInit() {
    this.http.get("http://localhost:8091/api/orderCustomers/"+this.customerId).subscribe(
      {
        next : customerD =>{
          this.customerDetails = customerD;
        },
        error : err => console.log(err)
      })
  }


  getTotal(order: any) {
    let total : number =0;
    for (let pi of order.productItems){
      total = total + (pi.price*pi.quantity)
    }

    return total
  }

  getOrder(order: any) {
    this.router.navigateByUrl("/order-details/"+order.id);

  }
}
