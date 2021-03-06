/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async, inject } from '@angular/core/testing';
import { OnInit } from '@angular/core';
import { DatePipe } from '@angular/common';
import { ActivatedRoute } from '@angular/router';
import { Observable } from 'rxjs/Rx';
import { JhiDateUtils, JhiDataUtils, JhiEventManager } from 'ng-jhipster';
import { TshopTestModule } from '../../../test.module';
import { MockActivatedRoute } from '../../../helpers/mock-route.service';
import { ResourceDetailComponent } from '../../../../../../main/webapp/app/entities/resource/resource-detail.component';
import { ResourceService } from '../../../../../../main/webapp/app/entities/resource/resource.service';
import { Resource } from '../../../../../../main/webapp/app/entities/resource/resource.model';

describe('Component Tests', () => {

    describe('Resource Management Detail Component', () => {
        let comp: ResourceDetailComponent;
        let fixture: ComponentFixture<ResourceDetailComponent>;
        let service: ResourceService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [TshopTestModule],
                declarations: [ResourceDetailComponent],
                providers: [
                    JhiDateUtils,
                    JhiDataUtils,
                    DatePipe,
                    {
                        provide: ActivatedRoute,
                        useValue: new MockActivatedRoute({id: 123})
                    },
                    ResourceService,
                    JhiEventManager
                ]
            }).overrideTemplate(ResourceDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(ResourceDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(ResourceService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
            // GIVEN

            spyOn(service, 'find').and.returnValue(Observable.of(new Resource('aaa')));

            // WHEN
            comp.ngOnInit();

            // THEN
            expect(service.find).toHaveBeenCalledWith(123);
            expect(comp.resource).toEqual(jasmine.objectContaining({id: 'aaa'}));
            });
        });
    });

});
