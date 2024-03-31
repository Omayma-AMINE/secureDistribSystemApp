import {Component, OnInit} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-customers',
  templateUrl: './customers.component.html',
  styleUrl: './customers.component.css'
})
export class CustomersComponent implements OnInit{
  customers : any;
  constructor(private http:HttpClient, private router:Router) {
  }

  ngOnInit() {
    this.http.get("http://localhost:8092/api/customers")
      .subscribe({
        next : data => {
          this.customers = data;
        },
        error : err => {
          console.log(err);
        }
      })
  }

  getOrders(customer: any) {
    this.router.navigateByUrl("/orders-by-customer/"+customer.customerId);
  }
}
