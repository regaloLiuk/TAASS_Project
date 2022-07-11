let url = "http://127.0.0.1:8081";

let shelter = "/api/v1/find/";
let comment = "/api/v1/comment/";
let reservation = "/api/v1/reservation/";

$(document).ready(function(){

   
    gapi.load('auth2', function() {
        console.log("caricato")
        gapi.auth2.init();
     });
      
   
    if(localStorage.getItem("mountBookId") == null || localStorage.getItem("mountBookId") == 0)
        window.location.href = 'login.html';
    else
        app.username = localStorage.getItem("mountBookUsername");
    
    console.log(localStorage.getItem("mountBookUsername"))


    var start_tmp =  new Date();
    var end_tmp= new Date();
    end_tmp.setDate(start_tmp.getDate() + 1);

    app.tmp_periodo = start_tmp.toLocaleString().replace("/","-").replace("/","-").substr(0,10).replace(",","")  +"  " +end_tmp.toLocaleString().replace("/","-").replace("/","-").substr(0,10).replace(",","");
     

    app.tmp_dump_periodo = start_tmp.toISOString().slice(0, 10)+" "+end_tmp.toISOString().slice(0, 10);
    app.dump_periodo=app.tmp_dump_periodo;
    console.log(app.tmp_dump_periodo);
    console.log(app.tmp_periodo);
});






$(function() {
    $('input[name="daterange"]').daterangepicker({
        "startDate": moment(),
        "endDate":moment().subtract(-1,"days"),
        "opens": "center",
        "locale": {
        "format": "DD/MM/YYYY",
        "daysOfWeek": [
            "Lun",
            "Mar",
            "Mer",
            "Gio",
            "Ven",
            "Sab",
            "Dom"
        ],
        "monthNames": [
            "Gennaio",
            "Febbraio",
            "Marzo",
            "Aprile",
            "Maggio",
            "Giugno",
            "Luglio",
            "Agosto",
            "Settembre",
            "Ottobre",
            "Novembre",
            "Dicembre"
        ], 
        "applyLabel": "Ok",
        "cancelLabel": "Annulla",
        "fromLabel": "Da",
        "toLabel": "A",
        "drops": "auto"
        },
       
    }, function (start, end, label) {
        console.log("A new date selection was made: " + start.format('DD-MM-YYYY') + ' to ' + end.format('DD-MM-YYYY'));
        app.tmp_dump_periodo =  start.format('YYYY-MM-DD') + ' ' + end.format('YYYY-MM-DD');
        app.tmp_periodo = start.format('DD-MM-YYYY') + ' ' + end.format('DD-MM-YYYY');
        console.log(start.format('YYYY-MM-DD') + ' ' + end.format('YYYY-MM-DD'))
        console.log(app.tmp_periodo);

    });
});


function reserve(){


    //--  Da recuperare valori veri
    var tmp_json = JSON.stringify({
        "user": "id",
        "guests": "numero ospiti",
        "firstDay": "Data",
        "lastDay": "Data",
        "structure": "id struttura",
    
    })

    $.post("url","json_body",
    function(data, status){
       console.log(data);
    });
    
}

function getStorico(){


    /* Nome struttura
       Data Inizio - Data Fine
    */

    $.get(url+"/api/v1/user/getUserHistory?userId="+localStorage.getItem("mountBookId"),
    function(data, status){
       console.log(data.reservations);
      // console.log(status);
       app.jsn_storico_dummy = data.reservations;
       console.log(app.jsn_storico_dummy[0].comments.clear);
       
    });

   
    
}


function find(){
    app.jsn_list_dummy = [];
    app.length=0;
    if(app.mappa_home!=null){
        console.log("Elimino i marker vecchi");
        app.deleteMap(app.mappa_home);
            
    }


    
/*
    
    $.post("url","json_body",
    function(data, status){
        app.jsn_list_dummy = JSON.parse(data);
    });
    */
   // app.jsn_list_dummy = JSON.parse("[{\"type\":\"0\",\"tel\":\"3321872123\",\"location\":\"Piobesi Torinese (TO)\",\"up\":\"23\",\"down\":\"7\",\"video\":\"https://youtu.be/embed/ZNuZ_NEoGWs\",\"service\":[\"Wi-Fi\",\"Noleggio attrezzatura\",\"Raggiungibile in macchina\"],\"onmap\":[\"7.074824\",\"45.191018\"],\"images\":[\"https://www.montagnaestate.it/wp-content/uploads/lago-konigssee-baviera-germania-paesaggio-in-estate.jpg\",\"https://www.montagnaestate.it/wp-content/uploads/baita-di-montagna-in-val-gardena.jpg\"], \"img\":\"https://www.montagnaestate.it/wp-content/uploads/lago-konigssee-baviera-germania-paesaggio-in-estate.jpg\",\"name\": \"Selleries\", \"id\": 1,\"descrizione\": \"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.\", \"prezzo\": \"56\"},{\"type\":\"1\",\"onmap\":[\"6.074824\",\"44.191018\"],\"id\": 2,\"name\": \"Jervis\", \"descrizione\": \"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.\",\"prezzo\": \"12\",\"img\":\"https://www.montagnaestate.it/wp-content/uploads/baita-di-montagna-in-val-gardena.jpg\"},{\"onmap\":[\"7.074824\",\"40.191018\"],\"id\": 3,\"name\": \"Barbara\", \"descrizione\": \"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.\",\"prezzo\": \"34\",\"img\":\"https://www.montagnaestate.it/wp-content/uploads/casa-nel-bosco.jpg\"},{\"img\":\"https://www.montagnaestate.it/wp-content/uploads/lago-konigssee-baviera-germania-paesaggio-in-estate.jpg\",\"name\": \"Selleries\", \"onmap\":[\"17.074824\",\"45.191018\"],\"id\": 1,\"descrizione\": \"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.\", \"prezzo\": \"24\"},{\"onmap\":[\"2.074824\",\"43.191018\"],\"id\": 2,\"name\": \"Jervis\", \"descrizione\": \"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.\",\"prezzo\": \"23\",\"img\":\"https://www.montagnaestate.it/wp-content/uploads/baita-di-montagna-in-val-gardena.jpg\"},{\"onmap\":[\"27.074824\",\"45.191018\"],\"id\": 3,\"name\": \"Barbara\", \"onmap\":[\"17.074824\",\"35.191018\"],\"descrizione\": \"Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.\",\"prezzo\": \"52\",\"img\":\"https://www.montagnaestate.it/wp-content/uploads/casa-nel-bosco.jpg\"}]")
   
    //############## type=0 TUTTI  ###
    //############## type=1 RIFUGIO  ###
    //############## type=2 BIVACCO  ###

    

    // RICERCA PER SERVIZIO

    const wi_fi = document.querySelector('#wi-fi');
    const macchina = document.querySelector('#macchina');
    const attrezzatura = document.querySelector('#noleggio_attrezzatura');

    let wf_chck
    let m_chck 
    let na_chck 

    (wi_fi.checked) ?  wf_chck=1  : wf_chck=0;
    (macchina.checked) ?  m_chck=1  : m_chck=0;
    (attrezzatura.checked) ?  na_chck=1  : na_chck=0;

    app.dump_tipo=$('#tipo').val();
    
    app.dump_periodo= app.tmp_dump_periodo;
    app.dump_residenti=$('#residenti').val();
    app.dump_costo_min=$('#costoMin').val();
    app.dump_costo_max=$('#costoMax').val();
    console.log("Tipo: "+app.dump_tipo+" Periodo: "+app.dump_periodo+" Residenti: "+app.dump_residenti+" Costo min: "+app.dump_costo_min+" Costo max: "+app.dump_costo_max);

    //let search = [{"wifi": wi_fi.checked, "car":macchina.checked, "equipment":attrezzatura.checked}]
        //console.log(app.marker);
        $("#dummy_space").height(700+50*app.marker.length);

        console.log(wf_chck+" "+m_chck+" "+ na_chck);
    let url_tmp = url+shelter+"findByService?wifi="+wf_chck+"&car="+m_chck+"&equipment="+na_chck;
    

  
    var tmp_json = JSON.stringify({
        "type":app.dump_tipo,
        "guest":app.dump_residenti,
        "minPrice":app.dump_costo_min,
        "maxPrice": app.dump_costo_max,
        "dateStart": app.dump_periodo.substr(0,10),
        "dateEnd": app.dump_periodo.substr(11,),
        "wifi": wi_fi.checked,
        "car": macchina.checked,
        "equipment": attrezzatura.checked
    
    })

    console.log("["+app.dump_periodo.substr(0,10)+"]")
    console.log("["+app.dump_periodo.substr(11,)+"]")

                                                                                    // IMPLEMENTARE CHIAMATA AJAX RICERCA
    $.ajax({
        method: "POST",
        url: url+shelter+"findStructure",
        async:true,
        data:tmp_json,
        crossDomain: true,
        contentType: "application/json",
        success: function(result) {
            console.log(result);
            app.jsn_list_dummy = result;

            for(var k in app.jsn_list_dummy) {
                app.length++; // Calcolo quanti elementi sono ritornati come risultato per visualizzarlo nella pagina
                    
                if(app.mappa_home==null)
                    app.mappa_home=app.createOpenStreetMap('map_home',k);
                else
                    app.addOpenStreetMapElement(app.mappa_home,k);

            }
            
        }
    });
   /* $.post(url+shelter+"findStructure",tmp_json,
                function(data, status){
                    console.log(data);
            });*/


    
}



function modifyCostoMinimo(costo){
    app.costoMin = costo;
    
}

function modifyCostoMassimo (costo){
    app.costoMax = costo;
}

//Gestisco eventi di navigazione con bottoni browser
window.addEventListener('hashchange', function(){
 console.log('location changed!');

    const url = new URL(window.location);
    console.log(url.hash);

    if(url.hash=="#Home" || url.hash=="")
        app.toHome();
    else if(url.hash=="#Dettaglio")
        app.toDetail(app.element);
    else if(url.hash=="#Riepilogo")
        app.toBook(app.element);
    else if(url.hash=="#Storico")
        app.toStorico();

});



//Definizione della componente di Vue.js
var app = new Vue({

    el: '#app',
    
    data: {
        username:"",
        seen_detail: false,
        seen_home:true,
        seen_book:false,
        seen_story:false,
        show_room_table:false,
        logged: false,
        img_src_detail:'',
        element:-1,
        length: 0,
        mappa_home:null,
        marker:marker = new Array(),
        imageUser: "'../img/usericon.png'",
        soggiorno:'Nessuno',
        n_notti:0,
        costoMin:1,
        costoMax:1,

        dump_tipo:null,
        dump_costo:null,
        dump_periodo:'Scegli..',
        tmp_dump_periodo:null,
        tmp_periodo:null,
        dump_residenti:null,


        jsn_list_dummy: null,
        jsn_room_dummy: null,
        jsn_storico_dummy:null,
        actual_page:null,

        id_user:-1,
        id_shelter:-1,

        costo: 0,

        },
    methods: {
        


        toDetail: function(index,id){// Passaggio da Home a Dettaglio

            app.seen_detail=true; // Rende visibile la porzione di pagina relativa al dettaglio
            app.seen_home=false; // Nasconde la porzione di pagina relativa alla home
            app.seen_book=false; // Nasconde la porzione di pagina relativa alla prenotazione
            
            app.id_shelter=app.jsn_list_dummy[parseInt(index)].id;

            app.img_src_detail=app.jsn_list_dummy[parseInt(index)].img; //Setta l'immagine da visualizzare nel dettaglio

            
            this.$nextTick(function () {app.createOpenStreetMap('map',app.element); }); // Richiamo l'aggiornamento della mappa in questo modo perchè è necessario attendere il rendere della porzione di pagina nuova per poter utilizzare il div 'map'
            app.marker =marker= new Array();
            
            app.element=parseInt(index); //Mantiene informazione sull'elemento scelto mantenendo la posizione nel json
           
            document.body.scrollTop = document.documentElement.scrollTop = 0;

        },

        toHome: function(index){// Passaggio da Dettaglio a Home

            app.seen_detail=false; // Nasconde la porzione di pagina relativa al dettaglio
            app.seen_book=false; // Nasconde la porzione di pagina relativa alla scelta
            app.seen_home=true; // Rende visibile la porzione di pagina relativa alla home
            app.seen_story=false;

            document.body.scrollTop = document.documentElement.scrollTop = 0;

                
                                        
        },

        toBook: function(){ //Effettuare chiamata ajax per recueperare info sulle stanze
            app.seen_detail=false; // Nasconde la porzione di pagina relativa al dettaglio
            app.seen_book=true;
            console.log("Siamo nel dettaglio? "+app.seen_book)
            
            /*var inizio = new Date(app.dump_periodo.substr(0,10)); 
            var fine = new Date(app.dump_periodo.substr(11)); 
             = fine-inizio;
            alert(app.n_notti*" inizio: "+app.dump_periodo.substr(0,10)+" fine: "+app.dump_periodo.substr(11));*/
            app.n_notti=app.datediff(app.parseDate(app.tmp_periodo.substr(0,10)),app.parseDate(app.tmp_periodo.substr(11)));


            console.log( app.dump_periodo.substr(0,10) +" "+app.dump_periodo.substr(11) );
            console.log(app.n_notti);
            
            document.body.scrollTop = document.documentElement.scrollTop = 0;


            var tmpCosto = app.n_notti * app.jsn_list_dummy[app.element].price * app.dump_residenti

            app.costo = Math.round((tmpCosto + Number.EPSILON) * 100) / 100;


        },

        toStorico: function(){ // Effettuare chiamata ajax per recueprare info sullo storico
            app.seen_detail=false; // Nasconde la porzione di pagina relativa al dettaglio
            app.seen_book=false;
            app.seen_home=false;
            app.seen_story=true;

            //Effettuare chiamata ajax per recupero storico
           // app.jsn_storico_dummy = JSON.parse("[{\"type\":\"0\",\"vote\":\"0\",\"voted\":\"0\",\"id\":\"0\",\"periodo\":\"12-12-2021 16-12-2021\",\"soggiorno\":\"Mezza pensione\",\"totale\":\"150\",\"posti\":\"4\",\"nome\":\"Selleries\",\"img\":\"https://www.rusconiarredamenti.it/img/545/arredamento-casa-letti-link-bside_oit_1128334.webp\"},{\"type\":\"1\",\"vote\":\"1\",\"voted\":\"1\",\"id\":\"1\",\"periodo\":\"09-12-2021 12-12-2021\",\"soggiorno\":\"Nessuno\",\"totale\":\"234\",\"posti\":\"2\",\"nome\":\"Ca D'Asti\",\"img\":\"https://www.rusconiarredamenti.it/img/545/arredamento-casa-letti-maronese-acf-vela_oit_1157661.webp\"},{\"type\":\"1\",\"vote\":\"0\",\"voted\":\"0\",\"id\":\"3\",\"periodo\":\"07-12-2021 08-12-2021\",\"posti\":\"2\",\"nome\":\"Bivacco 345\",\"img\":\"https://www.montagnaestate.it/wp-content/uploads/baita-di-montagna-in-val-gardena.jpg\"}]");
           getStorico(); 
           document.body.scrollTop = document.documentElement.scrollTop = 0;


        },

        createOpenStreetMap: function(mappa,index){ // Crea mappa con API OpenStreetMap
            // initialize Leaflet, lon=longitude, lat=latitude, zoom
            var lon = parseFloat(app.jsn_list_dummy[index].longitude);
            var lat = parseFloat(app.jsn_list_dummy[index].latitude);
            var name = app.jsn_list_dummy[index].name;
            var website = app.jsn_list_dummy[index].webSite

            
            var map = L.map(mappa).setView({lon: lon, lat: lat}, 5);

            console.log(lon+" "+lat+" "+" "+name);

            // add the OpenStreetMap tiles
            L.tileLayer('https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png', {
                maxZoom: 19,
                attribution: '&copy; <a href="https://openstreetmap.org/copyright">OpenStreetMap contributors</a>'
            }).addTo(map);

            // show the scale bar on the lower left corner
            L.control.scale({imperial: true, metric: true}).addTo(map);

            var mark = L.marker({lon: lon, lat: lat});
            marker.push(mark);//--
            mark.addTo(map).bindPopup(name+"<br><a href="+website+">Vai al sito</a>").openPopup(); // Da aggiungere link al sito 
            return map;
        },

        addOpenStreetMapElement: function(mappa,index){ // Aggiornamento mappa con API OpenStreetMap
            // initialize Leaflet, lon=longitude, lat=latitude, zoom
            var lon = parseFloat(app.jsn_list_dummy[index].longitude);
            var lat = parseFloat(app.jsn_list_dummy[index].latitude);
            var name = app.jsn_list_dummy[index].name;
            var website = app.jsn_list_dummy[index].webSite


            var mark = L.marker({lon: lon, lat: lat});
            marker.push(mark);
            mark.addTo(mappa).bindPopup(name+"<br><a href=\""+website+"\">Vai al sito</a>").openPopup(); // Da aggiungere link al sito 
        },

        deleteMap: function(mappa){ // Elimina mappa per poterla ricreare con API OpenStreetMap
            for(i=0;i<marker.length;i++) {
                mappa.removeLayer(marker[i]);
            } 
            app.marker =marker= new Array();

        },

        goToPositionMap: function(index){ // Sposta la mappa nella posizione del rifugio scelto
            var lon = parseFloat(app.jsn_list_dummy[index].longitude);
            var lat = parseFloat(app.jsn_list_dummy[index].latitude);

            app.mappa_home.setView({lon: lon, lat: lat}, 10);

            document.body.scrollTop = 0;

            var testDiv = document.getElementById("map_home");

            document.documentElement.scrollTop = testDiv.offsetTop;;
            
        },

        showRoomTable: function(){
            app.show_room_table=true;
        },

        hideRoomTable: function(){
            app.show_room_table=false;
        },

        selectRoom: function (index){
            console.log("Posti: "+app.jsn_room_dummy[index].posti+" Descrizione: "+app.jsn_room_dummy[index].description);
        },

        setSoggiorno: function(value){
            if(value==0)
                app.soggiorno="Nessuno";
            else if(value==1)
                app.soggiorno="Solo colazione";
            else if(value==2)
                app.soggiorno="Mezza pensione";
            else if(value==3)
                app.soggiorno="Pensione completa";
        },

        parseDate: function (str) { //Conversione per calcolo giorni di soggiorno
            var mdy = str.split('-');
            return new Date(mdy[2], mdy[1], mdy[0]);
        },

        datediff: function (first, second) { //Calcolo giorni di soggiorno
            // Take the difference between the dates and divide by milliseconds per day.
            // Round to nearest whole number to deal with DST.
            return Math.round((second-first)/(1000*60*60*24));
        },

        // Funzioni per valutazione struttura ---------
        vota: function(index){ // Modificare id prenotazione per recupero dati
            
            //app.jsn_storico_dummy[index]  CONTIENE INFO PRENOTAZIONE PER VOTO

            console.log("Indice "+index);
            console.log("ser"+index)
            var pulizia;
            var ospitalita;
            var cucina;
            var location;


            if(document.getElementsByName('pul'+index).item(0).checked)
                pulizia = true;
            else if(document.getElementsByName('pul'+index).item(1).checked)
                pulizia = false;

            if(document.getElementsByName('osp'+index).item(0).checked)
                ospitalita = true;
            else if(document.getElementsByName('osp'+index).item(1).checked)
                ospitalita = false;

            if(document.getElementsByName('cuc'+index).item(0).checked)
                cucina = true;
            else if(document.getElementsByName('cuc'+index).item(1).checked)
                cucina = false;

            if(document.getElementsByName('loc'+index).item(0).checked)
                location = true;
            else if(document.getElementsByName('loc'+index).item(1).checked)
                location = false;

          

            //-- RECUPERARE ID UTENTE E SHELTER
            var tmp_json =JSON.stringify({
                "id":app.jsn_storico_dummy[index].reservationId,
                "user":app.jsn_storico_dummy[index].userId,
                "structure":app.jsn_storico_dummy[index].structureId,
                "clear": pulizia,
                "ospitality": ospitalita,
                "food": cucina,
                "location": location,
   
            })

            $.ajax({
                method: "POST",
                url: url+comment+"doComment",
                async:true,
                data:tmp_json,
                crossDomain: true,
                contentType: "application/json",
                success: function(result) {
                    getStorico();
                        
                }
            });


            console.log(tmp_json)

            console.log("Elemento:"+ index +" Servizio: "+servizio + " Pulizia: "+pulizia+" Ospitalita: "+ospitalita+" Cucina: "+cucina+" Location: "+location);
            console.log(tmp_json);
            console.log(url+comment+"doComment");
        },

        eliminaPrenotazione: function(index, id_user, id){
            console.log("Eliminata prenotazione "+ index +" id utente: "+id_user+" id rifugio: "+id);
            
            var tmp_json = JSON.stringify({"userId":id_user,"reservationId":id})

            console.log(tmp_json);
            
            /*$.post(url+reservation+"deleteReservation",tmp_json,
                function(data, status){
                    console.log(data);
                    getStorico();
            });*/
            $.ajax({
                method: "POST",
                url: url+reservation+"deleteReservation",
                async:true,
                data:tmp_json,
                crossDomain: true,
                contentType: "application/json",
                success: function(result) {
                    console.log(result);
                    getStorico();
                }
            });

           
        },

        confermaPrenotazione: function(index){

            console.log("id "+app.id_shelter)

            var tmp_json =JSON.stringify(
                {"user":localStorage.getItem("mountBookId"),
                "structure":app.id_shelter,
                "guests":parseInt(app.dump_residenti),
                "firstDay": app.dump_periodo.substr(0,10), 
                "lastDay": app.dump_periodo.substr(11,)
            })

            $.ajax({
                method: "POST",
                url: url+reservation+"doReservation",
                async:true,
                data:tmp_json,
                crossDomain: true,
                contentType: "application/json",
                success: function(result) {
                    console.log(result);
                }
            });

            console.log("---> "+tmp_json);
                
        },

        getActualPage: function(){
            const url = new URL(window.location);
            console.log(url.hash);
        },

        signOut: function(){

            // Logout google
            var auth2 = gapi.auth2.getAuthInstance();
            auth2.signOut().then(function () {
              console.log('User signed out.');
            });

            localStorage.setItem("mountBookId",0);
            localStorage.setItem("mountBookUsername","");

            window.location.href = 'login.html'
        },

        createPdf: function(){

            var doc = new jsPDF();

            var rifugio = app.jsn_list_dummy[app.element].name;
            var tel = app.jsn_list_dummy[app.element].telephoneNumber;
            var location = app.jsn_list_dummy[app.element].address;
            var periodo = app.dump_periodo;
            var residenti = app.dump_residenti;
            var costo = app.jsn_list_dummy[app.element].price*app.n_notti*residenti;

           // var costo = Math.round((costo + Number.EPSILON) * 100) / 100;

            doc.setFontSize(18);

            doc.text(20, 60, 'Prenotazione a nome di: ' + localStorage.getItem("mountBookUsername"));
            doc.text(20, 70, 'Struttura: ' + rifugio);
            doc.text(20, 80, 'Telefono: ' + tel);
            doc.text(20, 90, 'Location: ' + location);
            doc.text(20, 100, 'Periodo: ' + periodo);
            doc.text(20, 110, 'Residenti: ' + residenti);
            doc.text(20, 140, '\t\t\t\t\tTotale: ' + app.costo + 'Euro');

            doc.line(20, 130, 160, 130); // horizontal line

                // Save the PDF
            doc.save('Prenotazione_'+rifugio+'.pdf');
            app.toHome();
            
        },
    }
})