Projenin verimli çalışması için database dizininden veritabanının kurulması verilerin eklenmesi gerekmektedir. 
Calendar tablosunu eklemez ise hata alacaktır.

ER scheması database dizininin altındadır

Aşağıda hangi kurallar hangi servisler altında yazılmış ekledim.



Kurallar
- Yıllık ücretli izne hak kazanmak için şirketimizde en az bir yıl çalışmış olmak gerekmektedir.
    **Cevap:**  Buradaki kontroller SchedulerService altında yazılmıştır.
- 
- Çalışanımız her yıl hizmet süresine göre aşağıda belirtilen izin sürelerine hak kazanır.
    **Cevap:**  Buradaki kontroller SchedulerService altında yazılmıştır.
- 
- Hafta sonu (Cumartesi, Pazar) ve resmi tatil günleri izin süresinden sayılmaz.
  **Cevap:** Bu kontroller Leaves service altındaki approveLeaves() fonksiyonunda yazılmıştır.
- 
- Yeni işe başlayan kişiler avans olarak 5 iş günü kadar izin kullanabilir. Bu uygulama sadece ilk yıl için geçerlidir
  **Cevap:** Bu case EmployeeService altındaki createEmployee içerisinde yazılmıştır.
- 
- İleri tarihli bir izin talebi yapıldığında, yapılan gün sayısı çalışanın hak ettiği izin gün sayısından düşer.
  **Cevap:** Bu kontroller Leaves service altındaki approveLeaves() fonksiyonunda yazılmıştır.
- 
- Çalışan izin girişi yaparken, kalan izin gün sayısından fazla izin talep edemez.
  **Cevap:** Bu kontroller Leaves service altındaki approveLeaves() fonksiyonunda yazılmıştır.
- 
- İzin talepleri yaratıldığı zaman “Onay Bekleniyor” statüsünde oluşur, yönetici onayından geçtikten
sonra “Onaylandı” veya “Reddedildi” statülerine düşer.
  **Cevap:** Bu kontroller Leaves service altındaki createLeaves() ve approveLeaves() fonksiyonlarında yazılmıştır.

