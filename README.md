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
- Listeleme sayfası için, internet connection state bir callback ile sürekli dinlemeye alındı.
  Connection available ise paging'i remote data üzerinden source ettim. Eğer connection unavailable ise
  paging'i kaydedilmiş data varsa room datası üzerinden source ettim. Bunlar için iki paging source mekanizması mevcut. Connectivity durumu listeleme
  sayfasında sağ üst köşedeki buluna wifi ikonu ile takip edilebilir. İkon tint kırmızı ise unavailable'ı,
  yeşil ise available'ı temsil etmektedir.
- Connection değişikliklerini hemen algılar ve ona göre hemen paging source kaynağını değiştirir.
- Hem retrofit ile beslerken hem de room ile beslerken geriye dönük 10 gün tarih filtrelemesi ile data gösterildi.
  Yine her iki durumda da fab buton ile tarih filtresi girilirse, girilen filtreye göre o tarih aralığındaki data gösterildi.

- Aşağıdaki görselde connection = unavailable ve room'da cache edecek herhangi bir veri bulunmuyor
<img width="244" alt="" src="https://github.com/kdrblt/NewsCompose/assets/132228860/8e657476-cd56-4fe6-943a-a57099e8b321">

- Aşağıdaki görselde connection = available ve paging retrofit'ten beslenmiş
<img width="373" alt="" src="https://github.com/kdrblt/NewsCompose/assets/132228860/9b27a9f7-f2c3-43cf-91ce-b2ec3efdbec8">

- Aşağıdaki görselde connection = available , date filter edilmiş, ve retrofitten filtered date ile paging beslenmiş.
  Eğer cancel iconu'na basılırsa, filtre kaldırılacak ve paging connection durumuna göre yeniden geriye doğru 10 gün ile beslenecek.
<img width="376" alt="Screenshot 2023-08-09 at 10 45 24" src="https://github.com/kdrblt/NewsCompose/assets/132228860/6a8c9e83-d085-46ac-b9ab-19f254558b73">

- Haber detay ekranında en altta bulunan url'ye tıklanırsa browser'a yönlendirir. 
<img width="382" alt="Screenshot 2023-08-09 at 10 45 39" src="https://github.com/kdrblt/NewsCompose/assets/132228860/d845457d-adc9-4931-a354-6597bee226c8">





