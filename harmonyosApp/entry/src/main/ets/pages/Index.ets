import { Greeting } from 'shared/src/main/ets/kmp-harmonyos-shared'

@Entry
@Component
struct Index {
  @State message: string = 'Hello World';

  build() {
    RelativeContainer() {
      Text(this.message)
        .id('HelloWorld')
        .fontSize(50)
        .fontWeight(FontWeight.Bold)
        .alignRules({
          center: { anchor: '__container__', align: VerticalAlign.Center },
          middle: { anchor: '__container__', align: HorizontalAlign.Center }
        })
      Button("Greeting").width(120).alignRules({
        middle: { anchor: '__container__', align: HorizontalAlign.Center }
      }).onClick(() => {
        let greeting = new Greeting()
        this.message = greeting.greet()
      })
    }
    .height('100%')
    .width('100%')
  }
}