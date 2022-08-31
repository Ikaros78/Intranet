// function sayHi(){
//     console.log(name);
//     // console.log(age);

//     var name = 'heechang';
//     // let age = 31;
// }

// sayHi(); // undefined, reference error

for(var i = 0; i < 3; i++){
    setTimeout(() => console.log(i), 1);
}

for(let i = 0; i < 3; i++){
    setTimeout(() => console.log(i), 1);
}

// var은 3 3 3 을 반환.
// let은 0 1 2 를 반환.


const shape = {
  radius: 10,
  diameter() {
    return this.radius * 2;
  },

  perimeter: () => 2 * Math.PI * this.radius,
};

console.log(shape.diameter());
console.log(shape.perimeter());

// 20 NAN. 화살표 함수에서 this 키워드는 주변 스코프를 참조한다.
// perimeter를 부를때 shape 객체가 아닌 주변을 둘러싼 스코프를 참조.
// 그리고 그 객체에는 radius라는 값이 없기 때문에 undefined를 반환하는 것.


