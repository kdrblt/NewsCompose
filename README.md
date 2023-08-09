# NewsCompose
# TechStack
- Jetpack Compose
- MVVM
- Hilt
- Flow
- Paging3
- Room
- Retrofit
- Coil
- Navigation

## Detaylar
- Uygulama listeleme ve haber detay ekranı olmak üzere toplamda 2 ekrandan oluşmaktadır
- Listeleme sayfası için, internet connection state bir callback ile sürekli dinlendi.
  Connection available ise paging'i varsa room'a cache ettiğim source ettim. Eğer connection unavailable ise
  paging'i retrefit ile besledim. Bunlar için iki source mekanizması mevcut. Connectivity durumu listeleme
  sayfasında sağ üst köşedeki buluna wifi ikonu ile takip edilebilir. İkon tint kırmızı ise unavailable'ı,
  yeşil ise available'ı temsil etmektedir.
<img width="244" alt="Unavailable - Room does'nt have any data" src="https://github.com/kdrblt/NewsCompose/assets/132228860/8e657476-cd56-4fe6-943a-a57099e8b321">


