/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { TshopTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { TResourceDetailComponent } from '../../../../../../main/webapp/app/entities/t-resource/t-resource-detail.component';
import { TResourceService } from '../../../../../../main/webapp/app/entities/t-resource/t-resource.service';
import { TResource } from '../../../../../../main/webapp/app/entities/t-resource/t-resource.model';

describe('Component Tests', () => {

    describe('TResource Management Detail Component', () => {
        let comp: TResourceDetailComponent;
        let fixture: ComponentFixture<TResourceDetailComponent>;
        let service: TResourceService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [TshopTestModule],
                declarations: [TResourceDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    TResourceService,
                    JhiEventManager
                ]
            }).overrideTemplate(TResourceDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(TResourceDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(TResourceService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new TResource('aaa')));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.tResource).toEqual(jasmine.objectContaining({id: 'aaa'}));
            });
        });
    });

});
