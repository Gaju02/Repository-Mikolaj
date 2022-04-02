const productNames =['olej_napedowy', 'benzyna_bezolowiowa','Gaz/LPG','odmrazacz_do_szyb', 'plyn_do_spryskiwaczy','olej_silnikowy'];
const clientNames = ["Stolmach sp.z.o.o","Semoniera S.A ","Maluta s.c. ", "GM sp.z.o.o", "Budwise"];

console.log(
    productNames[Math.floor(Math.random()*productNames.length)]
);
const taxes=[23,23,23,23,8,8,5];
console.log(taxes[Math.floor(Math.random()*taxes.length)]);

console.log(
    clientNames[Math.floor(Math.random()*clientNames.length)]
);

const actualDate = new Date();

function generation(){

    var date = document.getElementById("data").value;
    console.log(new Date(date).toLocaleDateString());

    console.log("-----------");
    var minDate = new Date(date);
    var maxDate = new Date();

    console.log(minDate);
    console.log(maxDate);


    console.log("-------------");

    var minDayDate  = new Date (minDate).getDate();
    var minMonthDate= new Date(minDate).getMonth()+1;
    var minYearDate = new Date(minDate).getFullYear();

    var maxDayDate = (new Date(maxDate)).getDate();
    var maxMonthDate =(new Date(maxDate).getMonth())+1;
    var maxYearDate = new Date(maxDate).getFullYear();

    console.log(maxDayDate);


    if(minYearDate<2000 || minYearDate>2022 || minDate>maxDate || minDate=="Invalid Date")
    {
        alert("Wybrano bledna date, prosze wybrac jeszcze raz ");
        return 0;
    }
    else {

    const makeInvoices=(invCount)=>{
        let invoices=[];
        for (i =0 ;i<invCount; i++){
            const inv={
                number: (Math.floor(Math.random()*100000000)), 
                buyerName: clientNames[Math.floor( Math.random() * 5)],
                products: productNames[Math.floor( Math.random() * 6)],
                //new Date(yearGiv +Math.floor(Math.random()*(2021-yearGiv)),monthGiv+Math.floor(Math.random()*(monthAct-monthGiv)),dayGiv+Math.floor(Math.random()*(dayAct-dayGiv))),
                //new Date(2000 +Math.floor(Math.random()*21),1+Math.floor(Math.random()*12),1+Math.floor(Math.random()*27)),
                saleDate: new Date(minYearDate+Math.floor(Math.random()*(maxYearDate-minYearDate)),minMonthDate+Math.floor(Math.random()*(maxMonthDate-minMonthDate)-1),minDayDate+Math.floor(Math.random()*(maxDayDate-minDayDate))),
                totalCostBefore : (Math.floor(Math.random()*100000))/100,
                tax : taxes[Math.floor( Math.random() * 7)],

            };
            invoices.push(inv);
    
        }
        return invoices;
    };
    


for (let invoice of makeInvoices(200)){

    
    //document.write(" number : "+invoice.number+" buyerName : "+ invoice.buyerName+" saleDate: "+invoice.saleDate +" totalCost : "+ invoice.totalCost);
    
    function printRow(invoice){
        var str = "<tr align=\"center\">";
        for(var i=0;i<invoice.length;i++){
            str += "<td>"+ invoice[i] + "</td>";
        }
        return str + "</tr>";
    }
        var oArr =[[invoice.number, 
            invoice.saleDate.toLocaleDateString(), 
            invoice.buyerName, invoice.products ,
            invoice.totalCostBefore, 
            invoice.tax, 
            Math.ceil(invoice.totalCostBefore*(invoice.tax/100)*100)/100,
            (invoice.totalCostBefore+ Math.ceil(invoice.totalCostBefore*(invoice.tax/100)*100)/100).toFixed(2)
            ]
        ];
        var arrLen = oArr.length;
        var str = "<table class=firstTable style=\"width:50%\"border= \"10px solid black\" align=\"center\">";
        str += "<tr><th>numer ID</th><th>Data sprzedaży </th><th>Firma</th><th>Produkt</th><th>Cena netto [zł]</th><th>Podatek [%]</th><th>Kwota podatku [zł]</th><th>Cena brutto [zł]</th></tr>"
        for (var i = 0; i < arrLen; i++){
            str += printRow(oArr[i]);
        }
        str += "</table>";
        document.getElementById("here").innerHTML+=str;
        console.log(invoice.saleDate);
}
}
}
