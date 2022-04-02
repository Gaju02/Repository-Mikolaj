function adding(){

    const A = document.getElementById('value1').value
    const B = document.getElementById('value2').value

    for ( let x =0; x <= A.length; x++){
        if(A[x]>1 ||A[x]< 0 || isNaN(A))
        {
            alert("Podano bledne liczby, prosze podac jeszcze raz ciag liczb z zakresu 0->1");
            return 1 ;
        }
        
    }
    for ( let y =0; y <= B.length; y++){
        if(B[y]>1 || B[y]< 0|| isNaN(B))
        {
            alert("Podano bledne liczby, prosze podac jeszcze raz ciag liczb z zakresu 0->1");
            return 1 ;
        }
    }


    
    const addBinary = (A, B) => {
        let N = 0;
        const sum = [];
        let l1 = A.length ;
        let l2 = B.length;



        for (let i=l1 - 1, j = l2 - 1;0 <= i || 0 <= j;--i,--j) {

            let a = 0 <= i ? Number(A[i]) : 0,

            b = 0 <= j ? Number(B[j]) : 0;

            sum.push((a + b + N) % 2);

            N = 1 < a + b + N;
        };
        if (N){
            sum.push(1);
        }
        return sum.reverse().join('');
        //document.getElementById("generate").innerHTML(result);
    };
    document.getElementById("text").innerHTML=addBinary(A, B);
}
